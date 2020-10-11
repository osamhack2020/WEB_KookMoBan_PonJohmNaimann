package dev.riyenas.osam.totp;

import dev.riyenas.osam.domain.admin.AdminRepository;
import dev.riyenas.osam.domain.auth.CryptoType;
import dev.riyenas.osam.domain.auth.TimeBasedOTP;
import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.device.DeviceRepository;
import dev.riyenas.osam.domain.soldier.SoldierRepository;
import dev.riyenas.osam.service.TimeBasedOTPService;
import dev.riyenas.osam.web.dto.iot.TOTPValidRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.yml")
public class TimeBasedOTPTest {
    private static final Logger log = LoggerFactory.getLogger(TimeBasedOTPTest.class);

    @Autowired
    private TimeBasedOTPService timeBasedOTPService;

    @Autowired
    private DeviceRepository deviceRepository;

    private String seed;

    @Before
    public void before() {
        seed = deviceRepository.save(Device.builder().build()).getUuid();
    }

    @Test
    public void createTimeBasedOTP() {
        String seed = "0123456789012345678901234567890123456789";
        Long T0 = 0L;
        Long X = 10L;
        Long testTime = 100L;

        String stepsA = TimeBasedOTP.calcSteps(testTime, T0, X);
        String timeBasedOTPA = TimeBasedOTP.generateTOTP(seed, stepsA, "8", CryptoType.HmacSHA512.toString());

        String stepsB = TimeBasedOTP.calcSteps(testTime + 9, T0, X);
        String timeBasedOTPB = TimeBasedOTP.generateTOTP(seed, stepsB, "8", CryptoType.HmacSHA512.toString());

        Assertions.assertEquals(timeBasedOTPA, timeBasedOTPB);
    }

    @Test
    public void isValidTimeBasedOtp() {
        Long timeInMillis = 1602338274597L; //Sat Oct 10 2020 22:57:54

        String steps = TimeBasedOTP.calcSteps(timeInMillis / 1000, 0L, 10L);
        String timeBasedOTP = TimeBasedOTP.generateTOTP(seed, steps, "8", CryptoType.HmacSHA512.toString());

        TOTPValidRequestDto dto = TOTPValidRequestDto.builder()
                .deviceId(1L)
                .timeInMillis(timeInMillis)
                .expectedTOTP(timeBasedOTP)
                .build();

        Assertions.assertTrue(timeBasedOTPService.isValidTimeBasedOtp(dto));
    }
}
