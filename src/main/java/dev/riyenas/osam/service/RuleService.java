package dev.riyenas.osam.service;

import dev.riyenas.osam.domain.rule.Rule;
import dev.riyenas.osam.domain.rule.RuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Log4j2
public class RuleService {
    private final RuleRepository ruleRepository;
    private final static Long DayToMillis = 86400000L;

    public boolean isValidUsingTime(Long millisTime, Long adminId) throws ParseException {
        Date now = millisToDate(millisTime);

        Rule rule = ruleRepository.findByAdminId(adminId).orElseThrow(() ->
                new IllegalArgumentException("관리자를 조회 할수 없습니다.")
        );

        Long nowTime = now.getTime() % DayToMillis;
        Long returnTime = rule.getReturnTime().getTime() % DayToMillis;
        Long dispensingTime = rule.getDispensingTime().getTime() % DayToMillis;

        return (dispensingTime < nowTime) && (nowTime < returnTime) ? true : false;
    }

    public static Date millisToDate(Long returnTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date timeInDate = new Date(returnTime);
        String timeInFormat = sdf.format(timeInDate);
        return new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(timeInFormat);
    }
}
