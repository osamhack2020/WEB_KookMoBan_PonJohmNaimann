package dev.riyenas.osam.web.dto.iot;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TOTPValidRequestDto {
    private String expectedTOTP;
    private Long timeInMillis;
    private Long deviceId;

    @Builder
    public TOTPValidRequestDto(String expectedTOTP, Long timeInMillis, Long deviceId) {
        this.expectedTOTP = expectedTOTP;
        this.timeInMillis = timeInMillis;
        this.deviceId = deviceId;
    }
}
