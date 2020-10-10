package dev.riyenas.osam.web;

import dev.riyenas.osam.service.TimeBasedOTPService;
import dev.riyenas.osam.web.dto.iot.TOTPValidRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/totp/")
public class APITimeBasedOTPAuthController {

    private final TimeBasedOTPService timeBasedOTPService;

    @GetMapping("generate")
    public String generate(@RequestParam String seed, @RequestParam Long timeInMillis) {
        return timeBasedOTPService.generateBySeedAndTimeInMills(seed, timeInMillis);
    }

    @GetMapping("generate/seed/{seed}")
    public String generate(@PathVariable String seed) {
        return timeBasedOTPService.generateByCurrentTime(seed);
    }

    @PostMapping("valid")
    public Boolean valid(@RequestBody TOTPValidRequestDto dto) {
        return timeBasedOTPService.isValidTimeBasedOtp(dto);
    }
}
