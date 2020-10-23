package dev.riyenas.osam.web.dto.app;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TOTPQRCodeDto {
    private String totp;
    private String qrcode;
    private Long adminId;
    private Long deviceId;

    @Builder
    public TOTPQRCodeDto(String totp, String qrcode, Long adminId, Long deviceId) {
        this.totp = totp;
        this.qrcode = qrcode;
        this.adminId = adminId;
        this.deviceId = deviceId;
    }
}
