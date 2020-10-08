package dev.riyenas.osam.service;

import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.device.DeviceRepository;
import dev.riyenas.osam.domain.soldier.Soldier;
import dev.riyenas.osam.domain.soldier.SoldierRepository;
import dev.riyenas.osam.web.dto.app.SoldierSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeviceReturnService {

    private final SoldierRepository soldierRepository;
    private final DeviceRepository deviceRepository;

    @Transactional
    public String createSoldier(SoldierSignUpRequestDto info) {

        Soldier soldier = soldierRepository.findByServiceNumber(info.getServiceNumber())
                .orElse(info.toSoldierEntity());

        Device device = deviceRepository.findBySerialNumber(info.getSerialNumber())
                .orElse(info.toDeviceEntity());

        soldier.addDevice(device);
        soldierRepository.save(soldier);

        return device.getUuid();
    }
}
