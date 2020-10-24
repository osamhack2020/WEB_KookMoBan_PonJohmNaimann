package dev.riyenas.osam.web.dto.device;

import dev.riyenas.osam.domain.device.Device;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DeviceResponseDto {
    private Long id;

    private String guid;
    private String type;
    private String manufacturer;
    private String uuid;

    public DeviceResponseDto(Device entity) {
        this.id = entity.getId();
        this.guid = entity.getGuid();
        this.type = entity.getType();
        this.manufacturer = entity.getManufacturer();
        this.uuid = entity.getUuid();
    }
}
