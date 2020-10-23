package dev.riyenas.osam.web;

import com.google.zxing.WriterException;
import dev.riyenas.osam.service.TimeBasedOTPService;
import dev.riyenas.osam.web.dto.app.TOTPQRCodeDto;
import dev.riyenas.osam.web.dto.iot.TOTPValidRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public String generateBySeed(@PathVariable String seed) {
        return timeBasedOTPService.generateByCurrentTime(seed);
    }

    @GetMapping("generate/device/{deviceId}")
    public String generateByDeviceId(@PathVariable Long deviceId) {
        return timeBasedOTPService.generateByCurrentTime(deviceId);
    }

    @GetMapping("generate/device/{deviceId}/qrcode")
    public TOTPQRCodeDto generateByDeviceIdQRCode(@PathVariable Long deviceId) throws IOException, WriterException {
        return timeBasedOTPService.genrateQRCode(deviceId);
    }

    @PostMapping("valid")
    public Boolean valid(@RequestBody TOTPValidRequestDto dto) {
        return timeBasedOTPService.isValidTimeBasedOtp(dto);
    }

    @GetMapping(value = "transfer/qrcode/{deviceId}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] qrcode(@PathVariable Long deviceId) throws WriterException, IOException {
        return timeBasedOTPService.transferQRCode(deviceId);
    }
}
