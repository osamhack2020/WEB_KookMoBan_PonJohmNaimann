package dev.riyenas.osam.totp;

import dev.riyenas.osam.domain.auth.CryptoType;
import dev.riyenas.osam.domain.auth.TimeBasedOTP;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeBasedOTPTest {
    private static final Logger log = LoggerFactory.getLogger(TimeBasedOTPTest.class);

    @Test
    public void createTimeBasedOTP() {
        String seed = "0123456789012345678901234567890123456789";
        long T0 = 0;
        long X = 10;
        long testTime = 100;

        String stepsA = TimeBasedOTP.calcSteps(testTime, T0, X);
        String timeBasedOTPA = TimeBasedOTP.generateTOTP(seed, stepsA, "8", CryptoType.HmacSHA512.toString());

        String stepsB = TimeBasedOTP.calcSteps(testTime + 9, T0, X);
        String timeBasedOTPB = TimeBasedOTP.generateTOTP(seed, stepsB, "8", CryptoType.HmacSHA512.toString());

        Assertions.assertEquals(timeBasedOTPA, timeBasedOTPB);
    }
}
