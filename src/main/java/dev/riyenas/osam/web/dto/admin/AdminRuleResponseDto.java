package dev.riyenas.osam.web.dto.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.riyenas.osam.domain.admin.Admin;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class AdminRuleResponseDto {
    private Long id;
    private String name;
    private String unit;
    private String signUpCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private Date dispensingTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private Date returnTime;


    public AdminRuleResponseDto(Admin entity) {
        this.id = entity.getId();
        this.unit = entity.getUnit();
        this.signUpCode = entity.getSignUpCode();
        this.name = entity.getName();
        this.dispensingTime = entity.getRule().getDispensingTime();
        this.returnTime = entity.getRule().getReturnTime();
    }
}
