package dev.riyenas.osam.web.dto.device;

import dev.riyenas.osam.domain.device.Device;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DeviceResponseDto {
    private Long id;

    private String serialNumber;
    private String type;
    private String manufacturer;
    private String phoneNumber;
    private String uuid;

    public DeviceResponseDto(Device entity) {
        this.id = entity.getId();
        this.serialNumber = entity.getSerialNumber();
        this.type = entity.getType();
        this.manufacturer = entity.getManufacturer();
        this.phoneNumber = entity.getPhoneNumber();
        this.uuid = entity.getUuid();
    }
}
