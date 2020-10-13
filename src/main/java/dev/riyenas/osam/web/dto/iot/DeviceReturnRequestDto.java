package dev.riyenas.osam.web.dto.iot;

import dev.riyenas.osam.domain.log.ReturnLog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public Date millisToDate(Long returnTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date timeInDate = new Date(returnTime);
        String timeInFormat = sdf.format(timeInDate);
        return new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(timeInFormat);
    }
}
