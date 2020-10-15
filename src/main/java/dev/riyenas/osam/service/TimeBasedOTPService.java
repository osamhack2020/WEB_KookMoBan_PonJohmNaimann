package dev.riyenas.osam.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dev.riyenas.osam.domain.auth.CryptoType;
import dev.riyenas.osam.domain.auth.TimeBasedOTP;
import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.device.DeviceRepository;
import dev.riyenas.osam.web.dto.app.QRCodeRequestDto;
import dev.riyenas.osam.web.dto.iot.TOTPValidRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    public String generateByCurrentTime(Long deviceId) {

        Device device = deviceRepository.findById(deviceId).orElseThrow(() ->
                new IllegalArgumentException("모바일 기기를 조회 할수 없습니다.")
        );

        Calendar time = Calendar.getInstance();

        String steps = TimeBasedOTP.calcSteps(time.getTimeInMillis() / 1000, 0L, 10L);

        return TimeBasedOTP.generateTOTP(device.getUuid(), steps, "8", CryptoType.HmacSHA512.toString());
    }

    public String generateBySeedAndTimeInMills(String seed, Long timeInMillis) {

        String steps = TimeBasedOTP.calcSteps(timeInMillis / 1000, 0L, 10L);

        return TimeBasedOTP.generateTOTP(seed, steps, "8", CryptoType.HmacSHA512.toString());
    }

    public Boolean isValidTimeBasedOtp(TOTPValidRequestDto dto) {

        Device device = deviceRepository.findById(dto.getDeviceId()).orElseThrow(() ->
                new IllegalArgumentException("모바일 기기를 조회 할수 없습니다.")
        );

        String steps = TimeBasedOTP.calcSteps(dto.getTimeInMillis() / 1000, 0L, 10L);
        String resultTOTP = TimeBasedOTP.generateTOTP(device.getUuid(), steps, "8", CryptoType.HmacSHA512.toString());

        String expectedTOTP = dto.getExpectedTOTP();

        return resultTOTP.equals(expectedTOTP);
    }

    public byte[] transferQRCode(Long deviceId) throws IOException, WriterException {
        Device device = deviceRepository.findById(deviceId).orElseThrow(() ->
                new IllegalArgumentException("모바일 기기를 조회 할수 없습니다.")
        );

        Long adminId = device.getSoldier().getAdmin().getId();

        Calendar time = Calendar.getInstance();

        String steps = TimeBasedOTP.calcSteps(time.getTimeInMillis() / 1000, 0L, 10L);
        String totp = TimeBasedOTP.generateTOTP(device.getUuid(), steps, "8", CryptoType.HmacSHA512.toString());

        QRCodeRequestDto dto = QRCodeRequestDto.builder()
                .deviceId(deviceId)
                .adminId(adminId)
                .TOTP(totp)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(dto);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(jsonStr, BarcodeFormat.QR_CODE, 300, 300);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        return pngOutputStream.toByteArray();
    }
}
