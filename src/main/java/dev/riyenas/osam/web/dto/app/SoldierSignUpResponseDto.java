package dev.riyenas.osam.web.dto.app;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SoldierSignUpResponseDto {
    private Long deviceId;
    private Long adminId;
    private String seed;

    @Builder
    public SoldierSignUpResponseDto(Long deviceId, Long adminId, String seed) {
        this.deviceId = deviceId;
        this.adminId = adminId;
        this.seed = seed;
    }
}
