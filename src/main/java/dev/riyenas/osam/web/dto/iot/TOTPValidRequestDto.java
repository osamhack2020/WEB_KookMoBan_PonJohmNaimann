package dev.riyenas.osam.web.dto.iot;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TOTPValidRequestDto {
    private String expectedTOTP;
    private Long timeInMillis;
    private String seed;

    @Builder
    public TOTPValidRequestDto(String expectedTOTP, Long timeInMillis, String seed) {
        this.expectedTOTP = expectedTOTP;
        this.timeInMillis = timeInMillis;
        this.seed = seed;
    }
}
