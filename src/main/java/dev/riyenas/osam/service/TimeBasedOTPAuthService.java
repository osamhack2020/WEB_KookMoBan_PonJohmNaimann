package dev.riyenas.osam.service;

import dev.riyenas.osam.domain.auth.CryptoType;
import dev.riyenas.osam.domain.auth.TimeBasedOTP;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Log4j2
@Service
@RequiredArgsConstructor
public class TimeBasedOTPAuthService {

    public String generateByCurrentTime(String seed) {

        Calendar time = Calendar.getInstance();

        String steps = TimeBasedOTP.calcSteps(time.getTimeInMillis() / 1000, 0L, 10L);

        log.info(time.getTime().toString());

        return TimeBasedOTP.generateTOTP(seed, steps, "8", CryptoType.HmacSHA512.toString());
    }

    public String generateBySeedAndTimeInMills(String seed, Long timeInMillis) {

        String steps = TimeBasedOTP.calcSteps(timeInMillis / 1000, 0L, 10L);

        return TimeBasedOTP.generateTOTP(seed, steps, "8", CryptoType.HmacSHA512.toString());
    }
}
