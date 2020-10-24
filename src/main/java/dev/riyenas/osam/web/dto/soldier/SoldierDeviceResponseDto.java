package dev.riyenas.osam.web.dto.soldier;

import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.soldier.Soldier;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SoldierDeviceResponseDto {
    private Long soldierId;
    private String serviceNumber;
    private String name;
    private String unit;
    private Long deviceId;
    private String guid;
    private String type;
    private String manufacturer;
    private String uuid;

    public SoldierDeviceResponseDto(Soldier soldier, Device device) {
        this.soldierId = soldier.getId();
        this.serviceNumber = soldier.getServiceNumber();
        this.name = soldier.getName();
        this.unit = soldier.getAdmin().getUnit();
        this.deviceId = device.getId();
        this.guid = device.getGuid();
        this.type = device.getType();
        this.manufacturer = device.getManufacturer();
        this.uuid = device.getUuid();
    }
}
