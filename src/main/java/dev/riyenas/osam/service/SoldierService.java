package dev.riyenas.osam.service;

import dev.riyenas.osam.domain.admin.Admin;
import dev.riyenas.osam.domain.admin.AdminRepository;
import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.device.DeviceRepository;
import dev.riyenas.osam.domain.soldier.Soldier;
import dev.riyenas.osam.domain.soldier.SoldierRepository;
import dev.riyenas.osam.web.dto.app.SoldierSignUpRequestDto;
import dev.riyenas.osam.web.dto.app.SoldierSignUpResponseDto;
import dev.riyenas.osam.web.dto.soldier.SoldierDeviceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SoldierService {

    private final SoldierRepository soldierRepository;
    private final DeviceRepository deviceRepository;
    private final AdminRepository adminRepository;

    @Transactional
    public SoldierSignUpResponseDto createSoldier(SoldierSignUpRequestDto info) {

        Admin admin = adminRepository.findBySignUpCode(info.getSignUpCode()).orElseThrow(() ->
                new IllegalArgumentException("정확한 회원가입 코드가 아닙니다.")
        );

        Soldier soldier = soldierRepository.findByServiceNumber(info.getServiceNumber())
                .orElse(info.toSoldierEntity());

        Device device = deviceRepository.findBySerialNumber(info.getSerialNumber())
                .orElse(info.toDeviceEntity());

        soldier.addDevice(device);
        soldier.setAdmin(admin);

        soldierRepository.save(soldier);

        return SoldierSignUpResponseDto.builder()
                .deviceId(device.getId())
                .adminId(admin.getId())
                .seed(device.getUuid())
                .build();
    }

    @Transactional(readOnly = true)
    public List<SoldierDeviceResponseDto> findByAdminServiceNumber(String adminServiceNumber) {
        List<SoldierDeviceResponseDto> dtos = new ArrayList<>();
        List<Soldier> soldiers = Optional.ofNullable(soldierRepository.findByAdminServiceNumber(adminServiceNumber))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 관리자가 지정된 병사를 조회할수 없습니다.")
                );

        return getSoldierDeviceResponseDtos(soldiers, dtos);
    }

    @Transactional(readOnly = true)
    public List<SoldierDeviceResponseDto> findAll() {
        List<SoldierDeviceResponseDto> dtos = new ArrayList<>();
        List<Soldier> soldiers = Optional.ofNullable(soldierRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() ->
                        new IllegalArgumentException("등록된 병사가 없습니다.")
                );

        return getSoldierDeviceResponseDtos(soldiers, dtos);
    }

    private List<SoldierDeviceResponseDto> getSoldierDeviceResponseDtos(List<Soldier> soldiers, List<SoldierDeviceResponseDto> dtos) {
        for(Soldier soldier : soldiers) {
            List<Device> devices = soldier.getDevices();

            for(Device device : devices) {
                SoldierDeviceResponseDto dto = new SoldierDeviceResponseDto(soldier, device);
                dtos.add(dto);
            }
        }

        return dtos;
    }
}
