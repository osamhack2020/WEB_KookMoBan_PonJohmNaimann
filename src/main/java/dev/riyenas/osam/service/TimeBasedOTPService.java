package dev.riyenas.osam.service;

import dev.riyenas.osam.domain.auth.CryptoType;
import dev.riyenas.osam.domain.auth.TimeBasedOTP;
import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.device.DeviceRepository;
import dev.riyenas.osam.web.dto.iot.TOTPValidRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Log4j2
@Service
@RequiredArgsConstructor
public class TimeBasedOTPService {

    private final DeviceRepository deviceRepository;

    public String generateByCurrentTime(String seed) {

        Calendar time = Calendar.getInstance();

        String steps = TimeBasedOTP.calcSteps(time.getTimeInMillis() / 1000, 0L, 10L);

        return TimeBasedOTP.generateTOTP(seed, steps, "8", CryptoType.HmacSHA512.toString());
    }

    public String generateBySeedAndTimeInMills(String seed, Long timeInMillis) {

        String steps = TimeBasedOTP.calcSteps(timeInMillis / 1000, 0L, 10L);

        return TimeBasedOTP.generateTOTP(seed, steps, "8", CryptoType.HmacSHA512.toString());
    }

    public Boolean isValidTimeBasedOtp(TOTPValidRequestDto dto) {

        String steps = TimeBasedOTP.calcSteps(dto.getTimeInMillis() / 1000, 0L, 10L);

        Device device = deviceRepository.findById(dto.getDeviceId()).orElseThrow(() ->
                new IllegalArgumentException("모바일 기기를 조회 할수 없습니다.")
        );

        String resultTOTP = TimeBasedOTP.generateTOTP(device.getUuid(), steps, "8", CryptoType.HmacSHA512.toString());
        String expectedTOTP = dto.getExpectedTOTP();

        return resultTOTP.equals(expectedTOTP);
    }
}
