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
    private String rank;
    private String unit;
    private String serialNumber;
    private String type;
    private String manufacturer;
    private String phoneNumber;

    @Builder
    public SoldierSignUpRequestDto(String name, String serviceNumber, String rank, String unit, String serialNumber, String type, String manufacturer, String phoneNumber) {
        this.name = name;
        this.serviceNumber = serviceNumber;
        this.rank = rank;
        this.unit = unit;
        this.serialNumber = serialNumber;
        this.type = type;
        this.manufacturer = manufacturer;
        this.phoneNumber = phoneNumber;
    }

    public Soldier toSoldierEntity() {
        return Soldier.builder()
                .name(this.name)
                .unit(this.unit)
                .rank(this.rank)
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
