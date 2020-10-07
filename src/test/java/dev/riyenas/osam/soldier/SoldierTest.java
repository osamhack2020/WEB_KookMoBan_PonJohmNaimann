package dev.riyenas.osam.soldier;

import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.device.DeviceRepository;
import dev.riyenas.osam.domain.soldier.Soldier;
import dev.riyenas.osam.domain.soldier.SoldierRepository;
import dev.riyenas.osam.web.dto.device.DeviceResponseDto;
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
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoldierTest {
    private static final Logger log = LoggerFactory.getLogger(SoldierTest.class);

    @Autowired
    private SoldierRepository soldierRepository;

    @Autowired
    private DeviceRepository deviceRepository;

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
    }
}
