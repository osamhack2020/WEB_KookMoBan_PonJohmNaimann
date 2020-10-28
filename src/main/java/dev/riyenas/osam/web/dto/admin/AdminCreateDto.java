package dev.riyenas.osam.web.dto.admin;

import dev.riyenas.osam.domain.admin.Admin;
import dev.riyenas.osam.domain.rule.Rule;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Calendar;
import java.util.Date;

@Getter
@ToString
public class AdminCreateDto {
    private String name;
    private String unit;
    private Long dispensingTime;
    private Long returnTime;

    public Admin toEntity() {

        String shortId = RandomStringUtils.randomNumeric(8);

        Admin admin = Admin.builder()
                .name(this.name)
                .unit(this.unit)
                .signUpCode(shortId)
                .build();

        Rule rule = Rule.builder()
                .dispensingTime(millisToDate(this.dispensingTime))
                .returnTime(millisToDate(this.returnTime))
                .admin(admin)
                .build();

        admin.setRule(rule);

        return admin;
    }

    public Date millisToDate(Long millisTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisTime);
        return new Date(calendar.getTimeInMillis());
    }
}
