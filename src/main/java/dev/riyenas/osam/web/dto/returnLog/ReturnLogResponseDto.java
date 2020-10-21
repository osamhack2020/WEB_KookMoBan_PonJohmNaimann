package dev.riyenas.osam.web.dto.returnLog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.riyenas.osam.domain.device.Device;
import dev.riyenas.osam.domain.log.ReturnLog;
import dev.riyenas.osam.domain.soldier.Soldier;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class ReturnLogResponseDto {
    private Long id;
    private String serviceNumber;
    private String name;
    private Date returnTime;
    private Double weight;
    private String state;
    private String serialNumber;
    private String type;
    private String manufacturer;
    private String phoneNumber;

    @JsonIgnore
    private String photo;

    public ReturnLogResponseDto(ReturnLog returnLog) {
        Soldier soldier = returnLog.getDevice().getSoldier();
        Device device = returnLog.getDevice();

        this.serviceNumber = soldier.getServiceNumber();
        this.name = soldier.getName();
        this.id = returnLog.getId();
        this.returnTime = returnLog.getReturnTime();
        this.photo = returnLog.getPhoto();
        this.weight = returnLog.getWeight();
        this.state = returnLog.getState().toString();
        this.serialNumber = device.getSerialNumber();
        this.type = device.getType();
        this.manufacturer = device.getManufacturer();
        this.phoneNumber = device.getPhoneNumber();
    }
}
