package dev.riyenas.osam.web;

import dev.riyenas.osam.service.TimeBasedOTPAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/totp/")
public class APITimeBasedOTPAuthController {

    private final TimeBasedOTPAuthService timeBasedOTPAuthService;

    @GetMapping("generate")
    public String generate(@RequestParam String seed, @RequestParam Long timeInMillis) {
        return timeBasedOTPAuthService.generateBySeedAndTimeInMills(seed, timeInMillis);
    }

    @GetMapping("generate/seed/{seed}")
    public String generate(@PathVariable String seed) {
        return timeBasedOTPAuthService.generateByCurrentTime(seed);
    }
}
