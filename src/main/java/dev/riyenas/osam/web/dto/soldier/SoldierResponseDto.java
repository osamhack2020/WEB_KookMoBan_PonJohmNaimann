
package dev.riyenas.osam.web.dto.soldier;

import dev.riyenas.osam.domain.soldier.Soldier;
import dev.riyenas.osam.web.dto.admin.AdminResponseDto;
import dev.riyenas.osam.web.dto.device.DeviceResponseDto;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class SoldierResponseDto {
    private Long id;
    private String serviceNumber;
    private String name;
    private String rank;
    private String unit;
    private AdminResponseDto admin;
    private List<DeviceResponseDto> devices;

    public SoldierResponseDto(Soldier entity) {
        this.id = entity.getId();
        this.serviceNumber = entity.getServiceNumber();
        this.name = entity.getName();
        this.rank = entity.getRank();
        this.unit = entity.getUnit();
        this.devices = entity.getDevices().stream()
                .map(DeviceResponseDto::new)
                .collect(Collectors.toList());
        this.admin = new AdminResponseDto(entity.getAdmin());
    }
}