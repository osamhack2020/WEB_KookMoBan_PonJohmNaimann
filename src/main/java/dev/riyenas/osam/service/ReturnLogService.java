package dev.riyenas.osam.service;

import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.device.DeviceRepository;
import dev.riyenas.osam.domain.log.ReturnLog;
import dev.riyenas.osam.domain.log.ReturnLogRepository;
import dev.riyenas.osam.web.dto.iot.DeviceReturnRequestDto;
import dev.riyenas.osam.web.dto.returnLog.ReturnLogResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class ReturnLogService {

    private final ReturnLogRepository returnLogRepository;
    private final DeviceRepository deviceRepository;

    public byte[] saveReturnLog(DeviceReturnRequestDto dto) throws ParseException {
        String base64Image = dto.getPhoto().split(",")[1];

        Device device = deviceRepository.findById(dto.getDeviceId()).orElseThrow(() ->
                new IllegalArgumentException("디바이스를 찾을수 없습니다.")
        );

        ReturnLog returnLog = dto.toEntity();
        returnLog.setDevice(device);

        byte[] bytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);

        returnLogRepository.save(returnLog);

        return bytes;
    }

    public ReturnLogResponseDto findById(Long id) {
        ReturnLog returnLog = returnLogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("반납 기록을 조회 할수 없습니다.")
        );

        return new ReturnLogResponseDto(returnLog);
    }
}
