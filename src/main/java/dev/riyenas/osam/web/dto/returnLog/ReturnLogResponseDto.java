package dev.riyenas.osam.web.dto.returnLog;

import dev.riyenas.osam.domain.log.ReturnLog;
import dev.riyenas.osam.web.dto.device.DeviceResponseDto;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class ReturnLogResponseDto {
    private DeviceResponseDto device;
    private Date returnTime;
    private Double weight;
    private String photo;

    public ReturnLogResponseDto(ReturnLog returnLog) {
        this.device = new DeviceResponseDto(returnLog.getDevice());
        this.returnTime = returnLog.getReturnTime();
        this.photo = returnLog.getPhoto();
        this.weight = returnLog.getWeight();
    }
}
