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
    private String type;
    private String manufacturer;
    private String guid;
    private String signUpCode;

    @Builder
    public SoldierSignUpRequestDto(String name, String serviceNumber, String type, String manufacturer, String guid, String signUpCode) {
        this.name = name;
        this.serviceNumber = serviceNumber;
        this.type = type;
        this.manufacturer = manufacturer;
        this.guid = guid;
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
                .guid(this.guid)
                .type(this.type)
                .build();
    }
}
