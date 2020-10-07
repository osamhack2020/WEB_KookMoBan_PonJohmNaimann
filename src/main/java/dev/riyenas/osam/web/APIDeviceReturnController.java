package dev.riyenas.osam.web;

import dev.riyenas.osam.service.DeviceReturnService;
import dev.riyenas.osam.web.dto.app.SoldierSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/soldier/")
public class APIDeviceReturnController {

    private final DeviceReturnService deviceReturnService;

    @PostMapping("create")
    public String create(@RequestBody SoldierSignUpRequestDto soldierSignUpRequestDto) {
        return deviceReturnService.createSoldier(soldierSignUpRequestDto);
    }
}
