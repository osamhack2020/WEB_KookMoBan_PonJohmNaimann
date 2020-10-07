package dev.riyenas.osam.device;

import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.device.DeviceRepository;
import dev.riyenas.osam.web.dto.device.DeviceCreateRequestDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceTest {
    private static final Logger log = LoggerFactory.getLogger(DeviceTest.class);

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void deviceCreate() {
        DeviceCreateRequestDto dto = DeviceCreateRequestDto.builder()
                .manufacturer("삼성")
                .serialNumber("S/NABCDEFGHIJKLMN")
                .type("phone")
                .phoneNumber("010-1234-5678")
                .build();

        Device device = deviceRepository.save(dto.toEntity());

        Assertions.assertEquals(dto.getSerialNumber(), device.getSerialNumber());
    }
}