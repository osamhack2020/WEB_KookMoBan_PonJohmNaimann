package dev.riyenas.osam.web;

import dev.riyenas.osam.service.ReturnLogService;
import dev.riyenas.osam.service.RuleService;
import dev.riyenas.osam.service.SoldierService;
import dev.riyenas.osam.web.dto.app.SoldierSignUpRequestDto;
import dev.riyenas.osam.web.dto.app.SoldierSignUpResponseDto;
import dev.riyenas.osam.web.dto.iot.DeviceReturnRequestDto;
import dev.riyenas.osam.web.dto.returnLog.ReturnLogResponseDto;
import dev.riyenas.osam.web.dto.soldier.SoldierDeviceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/soldier/")
public class APIDeviceReturnController {

    private final SoldierService soldierService;
    private final ReturnLogService returnLogService;
    private final RuleService ruleService;

    @PostMapping("create")
    public SoldierSignUpResponseDto create(@RequestBody SoldierSignUpRequestDto soldierSignUpRequestDto) {
        return soldierService.createSoldier(soldierSignUpRequestDto);
    }

    @GetMapping("find/all")
    public List<SoldierDeviceResponseDto> findAll() {
        return soldierService.findAll();
    }

    @GetMapping("find/admin/serviceNumber/{adminServiceNumber}")
    public List<SoldierDeviceResponseDto> findByAdminServiceNumber(@PathVariable String adminServiceNumber) {
        log.info(adminServiceNumber);
        return soldierService.findByAdminServiceNumber(adminServiceNumber);
    }

    @GetMapping("find/admin/signUpCode/{signUpCode}")
    public List<SoldierDeviceResponseDto> findBySignUpCode(@PathVariable String signUpCode) {
        return soldierService.findBySignUpCode(signUpCode);
    }

    @PostMapping(value = "device/log/create", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] returnLogCreate(@RequestBody DeviceReturnRequestDto dto) throws ParseException {
        return returnLogService.saveReturnLog(dto);
    }

    @GetMapping("device/log/find/id/{id}")
    public ReturnLogResponseDto test(@PathVariable Long id) {
        return returnLogService.findById(id);
    }

    @GetMapping("return/time/valid")
    public boolean isValidUseTime(@RequestParam Long millis, @RequestParam Long adminId) throws ParseException {
        return ruleService.isValidUsingTime(millis, adminId);
    }
}
