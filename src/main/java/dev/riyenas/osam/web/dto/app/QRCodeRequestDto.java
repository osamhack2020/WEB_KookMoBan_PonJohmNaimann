package dev.riyenas.osam.web.dto.app;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QRCodeRequestDto {
    private Long deviceId;
    private String TOTP;

    @Builder
    public QRCodeRequestDto(Long deviceId, String TOTP) {
        this.deviceId = deviceId;
        this.TOTP = TOTP;
    }
}
