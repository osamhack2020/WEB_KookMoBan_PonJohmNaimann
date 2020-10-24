package dev.riyenas.osam.soldier;

import dev.riyenas.osam.domain.admin.Admin;
import dev.riyenas.osam.domain.admin.AdminRepository;
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
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoldierTest {
    private static final Logger log = LoggerFactory.getLogger(SoldierTest.class);

    @Autowired
    private SoldierRepository soldierRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SoldierService soldierService;

    @Test
    @Transactional
    public void soldierCreate() {
        Soldier soldier = Soldier.builder()
                .serviceNumber("19-123456")
                .name("홍길동")
                .build();

        Device device = Device.builder()
                .manufacturer("삼성")
                .guid("S/NABCDEFGHIJKLMN")
                .type("phone")
                .build();

        Admin admin = Admin.builder()
                .name("관리자")
                .serviceNumber("19-123456")
                .password("123456")
                .signUpCode("8888888")
                .unit("국직")
                .build();

        soldier.addDevice(device);
        soldier.setAdmin(admin);

        Soldier soldierInfo = soldierRepository.save(soldier);

        log.info(new SoldierResponseDto(soldierInfo).toString());

        Assertions.assertEquals(soldier.getServiceNumber(), soldierInfo.getServiceNumber());
    }

    @Test
    @Transactional
    public void deleteOrphanRemovalTest() {
        Soldier soldier = Soldier.builder()
                .serviceNumber("19-123456")
                .name("홍길동")
                .build();

        Device device1 = Device.builder()
                .manufacturer("삼성")
                .guid("S/NABCDEFGHIJKLMN")
                .type("phone")
                .build();

        Device device2 = Device.builder()
                .manufacturer("삼성")
                .guid("S/NABCDEFGHIJKLMN")
                .type("phone")
                .build();

        soldier.addDevice(device1);
        soldier.addDevice(device2);

        Long soldierId = soldierRepository.save(soldier).getId();
        soldierRepository.deleteById(soldierId);

        List<Device> devices = deviceRepository.findAll();

        Assertions.assertTrue(devices.isEmpty());
    }
}
