package dev.riyenas.osam.soldier;

import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.device.DeviceRepository;
import dev.riyenas.osam.domain.soldier.Soldier;
import dev.riyenas.osam.domain.soldier.SoldierRepository;
import dev.riyenas.osam.service.SoldierService;
import dev.riyenas.osam.web.dto.app.SoldierSignUpRequestDto;
import dev.riyenas.osam.web.dto.soldier.SoldierResponseDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoldierTest {
    private static final Logger log = LoggerFactory.getLogger(SoldierTest.class);

    @Autowired
    private SoldierRepository soldierRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SoldierService soldierService;

    @Test
    public void soldierCreate() {
        Soldier soldier = Soldier.builder()
                .serviceNumber("19-123456")
                .rank("병장")
                .unit("국직")
                .name("홍길동")
                .build();

        Device device = Device.builder()
                .manufacturer("삼성")
                .serialNumber("S/NABCDEFGHIJKLMN")
                .type("phone")
                .phoneNumber("010-1234-5678")
                .build();

        soldier.addDevice(device);

        Soldier soldierInfo = soldierRepository.save(soldier);

        log.info(new SoldierResponseDto(soldierInfo).toString());

        Assertions.assertEquals(soldier.getServiceNumber(), soldierInfo.getServiceNumber());
    }

    @Test
    @Transactional
    public void deleteOrphanRemovalTest() {
        Soldier soldier = Soldier.builder()
                .serviceNumber("19-123456")
                .rank("병장")
                .unit("국직")
                .name("홍길동")
                .build();

        Device device1 = Device.builder()
                .manufacturer("삼성")
                .serialNumber("S/NABCDEFGHIJKLMN")
                .type("phone")
                .phoneNumber("010-1234-5678")
                .build();

        Device device2 = Device.builder()
                .manufacturer("삼성")
                .serialNumber("S/NABCDEFGHIJKLMN")
                .type("phone")
                .phoneNumber("010-1234-5678")
                .build();

        soldier.addDevice(device1);
        soldier.addDevice(device2);

        soldierRepository.save(soldier);
        soldierRepository.deleteById(1L);

        List<Device> devices = deviceRepository.findAll();

        Assertions.assertTrue(devices.isEmpty());
    }

    @Test
    @Transactional
    public void strategySoldierCreate1() {
        SoldierSignUpRequestDto dto1 = SoldierSignUpRequestDto.builder()
                .serviceNumber("20-111111")
                .serialNumber("A")
                .build();

        SoldierSignUpRequestDto dto2 = SoldierSignUpRequestDto.builder()
                .serviceNumber("20-111111")
                .serialNumber("B")
                .build();

        soldierService.createSoldier(dto1);

        soldierService.createSoldier(dto2);

        Optional<Soldier> soldier = soldierRepository.findById(1L);

        Assertions.assertEquals(soldier.get().getDevices().get(0).getSerialNumber(), "A");
        Assertions.assertEquals(soldier.get().getDevices().get(1).getSerialNumber(), "B");
    }

    @Test
    @Transactional
    public void strategySoldierCreate2() {
        SoldierSignUpRequestDto dto1 = SoldierSignUpRequestDto.builder()
                .serviceNumber("20-111111")
                .serialNumber("A")
                .build();

        SoldierSignUpRequestDto dto2 = SoldierSignUpRequestDto.builder()
                .serviceNumber("20-222222")
                .serialNumber("A")
                .build();

        soldierService.createSoldier(dto1);

        soldierService.createSoldier(dto2);

        Optional<Soldier> soldier1 = soldierRepository.findById(1L);
        Optional<Soldier> soldier2 = soldierRepository.findById(2L);

        Assertions.assertEquals(soldier1.get().getDevices().get(0).getSerialNumber(), "A");
        Assertions.assertEquals(soldier2.get().getDevices().get(0).getSerialNumber(), "A");
    }
}
