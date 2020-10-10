package dev.riyenas.osam.web;

import dev.riyenas.osam.service.SoldierService;
import dev.riyenas.osam.web.dto.app.SoldierSignUpRequestDto;
import dev.riyenas.osam.web.dto.soldier.SoldierDeviceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/soldier/")
public class APIDeviceReturnController {

    private final SoldierService soldierService;

    @PostMapping("create")
    public String create(@RequestBody SoldierSignUpRequestDto soldierSignUpRequestDto) {
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
}
