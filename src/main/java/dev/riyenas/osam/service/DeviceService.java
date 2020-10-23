package dev.riyenas.osam.service;

import dev.riyenas.osam.domain.admin.Admin;
import dev.riyenas.osam.domain.admin.AdminRepository;
import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.device.DeviceRepository;
import dev.riyenas.osam.domain.log.ReturnLog;
import dev.riyenas.osam.domain.log.ReturnLogRepository;
import dev.riyenas.osam.domain.soldier.Soldier;
import dev.riyenas.osam.web.dto.device.DeviceLogResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final AdminRepository adminRepository;
    private final ReturnLogRepository returnLogRepository;

    public List<DeviceLogResponseDto> returnStatus(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() ->
                new IllegalArgumentException("관리자를 조회할수 없습니다.")
        );

        List<DeviceLogResponseDto> deviceLogResponseDtos = new ArrayList<>();

        for(Soldier soldier : admin.getSoldiers()) {
            for(Device device : soldier.getDevices()) {
                ReturnLog returnLog = returnLogRepository.findByDeviceIdOne(device.getId()).orElseGet(() -> {
                    ReturnLog nonReturn = ReturnLog.builder().build();
                    nonReturn.stateNonPass();
                    return nonReturn;
                });

                deviceLogResponseDtos.add(
                        new DeviceLogResponseDto(device, returnLog)
                );
            }
        }

        return deviceLogResponseDtos;
    }

    public List<DeviceLogResponseDto> returnStatusAll() {

        List<DeviceLogResponseDto> deviceLogResponseDtos = new ArrayList<>();

        List<Device> devices = deviceRepository.findAll();

        for(Device device : devices) {
            ReturnLog returnLog = returnLogRepository.findByDeviceIdOne(device.getId()).orElseGet(() -> {
                ReturnLog nonReturn = ReturnLog.builder().build();
                nonReturn.stateNonPass();
                return nonReturn;
            });

            deviceLogResponseDtos.add(
                    new DeviceLogResponseDto(device, returnLog)
            );
        }

        return deviceLogResponseDtos;
    }
}
