package dev.riyenas.osam.web.dto.returnLog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.riyenas.osam.domain.log.ReturnLog;
import dev.riyenas.osam.web.dto.device.DeviceResponseDto;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class ReturnLogResponseDto {
    private Long id;
    private Date returnTime;
    private Double weight;
    private String state;
    private DeviceResponseDto device;

    @JsonIgnore
    private String photo;

    public ReturnLogResponseDto(ReturnLog returnLog) {
        this.id = returnLog.getId();
        this.returnTime = returnLog.getReturnTime();
        this.photo = returnLog.getPhoto();
        this.weight = returnLog.getWeight();
        this.state = returnLog.getState().toString();
        this.device = new DeviceResponseDto(returnLog.getDevice());
    }
}
