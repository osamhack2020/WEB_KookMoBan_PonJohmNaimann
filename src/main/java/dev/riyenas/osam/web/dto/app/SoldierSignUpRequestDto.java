package dev.riyenas.osam.web.dto.app;

import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.soldier.Soldier;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SoldierSignUpRequestDto {
    private String name;
    private String serviceNumber;
    private String serialNumber;
    private String type;
    private String manufacturer;
    private String phoneNumber;
    private String signUpCode;

    @Builder
    public SoldierSignUpRequestDto(String name, String serviceNumber, String serialNumber, String type,
                                   String manufacturer, String phoneNumber, String signUpCode) {
        this.name = name;
        this.serviceNumber = serviceNumber;
        this.serialNumber = serialNumber;
        this.type = type;
        this.manufacturer = manufacturer;
        this.phoneNumber = phoneNumber;
        this.signUpCode = signUpCode;
    }

    public Soldier toSoldierEntity() {
        return Soldier.builder()
                .name(this.name)
                .serviceNumber(this.serviceNumber)
                .build();
    }

    public Device toDeviceEntity() {
        return Device.builder()
                .manufacturer(this.manufacturer)
                .phoneNumber(this.phoneNumber)
                .serialNumber(this.serialNumber)
                .type(this.type)
                .build();
    }
}
