package dev.riyenas.osam.web.dto.iot;

import dev.riyenas.osam.domain.log.ReturnLog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.ParseException;

import static dev.riyenas.osam.service.RuleService.millisToDate;

@Getter
@NoArgsConstructor
public class DeviceReturnRequestDto {
    private Long deviceId;
    private Long returnTime;
    private Double weight;
    private String photo;

    public ReturnLog toEntity() throws ParseException {
        return ReturnLog.builder()
                .returnTime(millisToDate(returnTime))
                .weight(weight)
                .photo(photo)
                .build();
    }
}
