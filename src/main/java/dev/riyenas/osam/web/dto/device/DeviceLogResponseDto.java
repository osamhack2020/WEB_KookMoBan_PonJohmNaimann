package dev.riyenas.osam.web.dto.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.log.ReturnLog;
import dev.riyenas.osam.domain.soldier.Soldier;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class DeviceLogResponseDto {
    private Long id;
    private String name;
    private String serviceNumber;
    private String guid;
    private String manufacturer;
    private String state;
    private String icon;
    private Long order;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private Date returnTime;

    @Builder
    public DeviceLogResponseDto(Device entity, ReturnLog returnLog) {
        Soldier soldier = entity.getSoldier();

        this.id = entity.getId();
        this.name = soldier.getName();
        this.serviceNumber = soldier.getServiceNumber();
        this.guid = entity.getGuid();
        this.manufacturer = entity.getManufacturer();
        this.state = returnLog.getState().toString();
        this.returnTime = returnLog.getReturnTime();
        this.icon = returnLog.getState().getIcon();
        this.order = returnLog.getState().getOrder();
    }
}
