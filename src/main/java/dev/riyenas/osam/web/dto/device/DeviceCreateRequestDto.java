package dev.riyenas.osam.web.dto.device;

import dev.riyenas.osam.domain.device.Device;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeviceCreateRequestDto {
    private String serialNumber;
    private String type;
    private String manufacturer;
    private String phoneNumber;

    @Builder
    public DeviceCreateRequestDto(String serialNumber, String type, String manufacturer, String phoneNumber) {
        this.serialNumber = serialNumber;
        this.type = type;
        this.manufacturer = manufacturer;
        this.phoneNumber = phoneNumber;
    }

    public Device toEntity() {
        return Device.builder()
                .manufacturer(this.manufacturer)
                .phoneNumber(this.phoneNumber)
                .serialNumber(this.serialNumber)
                .type(this.type)
                .build();
    }
}
