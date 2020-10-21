package dev.riyenas.osam.service;

import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.device.DeviceRepository;
import dev.riyenas.osam.domain.log.ReturnLog;
import dev.riyenas.osam.domain.log.ReturnLogRepository;
import dev.riyenas.osam.domain.rule.Rule;
import dev.riyenas.osam.web.dto.iot.DeviceReturnRequestDto;
import dev.riyenas.osam.web.dto.returnLog.ReturnLogDetailResponseDto;
import dev.riyenas.osam.web.dto.returnLog.ReturnLogResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static dev.riyenas.osam.service.RuleService.millisToDate;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReturnLogService {

    private final ReturnLogRepository returnLogRepository;
    private final DeviceRepository deviceRepository;

    private final static Long DayToMillis = 86400000L;

    @Transactional
    public byte[] saveReturnLog(DeviceReturnRequestDto dto) throws ParseException {
        String base64Image = dto.getPhoto().split(",")[1];

        Device device = deviceRepository.findById(dto.getDeviceId()).orElseThrow(() ->
                new IllegalArgumentException("디바이스를 찾을수 없습니다.")
        );

        Rule rule = device.getSoldier().getAdmin().getRule();

        ReturnLog returnLog = dto.toEntity();
        returnLog.setDevice(device);


        if(!isDeviceUsingTime(dto.getReturnTime(), rule)) {
            returnLog.stateDelay();
        } else {
            returnLog.statePass();
        }

        byte[] bytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);

        returnLogRepository.save(returnLog);

        return bytes;
    }

    public static boolean isDeviceUsingTime(Long millis, Rule rule) throws ParseException {

        Date returnMillis = millisToDate(millis);

        long soldierReturnTime = returnMillis.getTime() % DayToMillis;
        long returnTime = rule.getReturnTime().getTime() % DayToMillis;
        long dispensingTime = rule.getDispensingTime().getTime() % DayToMillis;

        return (dispensingTime < soldierReturnTime) && (soldierReturnTime < returnTime);
    }

    public ReturnLogDetailResponseDto findById(Long id) {
        ReturnLog returnLog = returnLogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("반납 기록을 조회 할수 없습니다.")
        );

        return new ReturnLogDetailResponseDto(returnLog);
    }

    @Transactional(readOnly = true)
    public List<ReturnLogResponseDto> findAll() {
        return returnLogRepository.findAll().stream()
                .map(ReturnLogResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReturnLogResponseDto> findByDeviceId(Long deviceId) {
        return returnLogRepository.findByDeviceId(deviceId).stream()
                .map(ReturnLogResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReturnLogResponseDto> findByReturnFault() {
        return returnLogRepository.findByReturnFault().stream()
                .map(ReturnLogResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReturnLogResponseDto> findByReturnPass() {
        return returnLogRepository.findByReturnPass().stream()
                .map(ReturnLogResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAll() {
        returnLogRepository.deleteAll();
    }
}
