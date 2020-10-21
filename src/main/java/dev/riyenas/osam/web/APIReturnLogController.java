package dev.riyenas.osam.web;

import dev.riyenas.osam.service.ReturnLogService;
import dev.riyenas.osam.web.dto.iot.DeviceReturnRequestDto;
import dev.riyenas.osam.web.dto.returnLog.ReturnLogDetailResponseDto;
import dev.riyenas.osam.web.dto.returnLog.ReturnLogResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/log/")
public class APIReturnLogController {

    private final ReturnLogService returnLogService;

    @PostMapping(value = "create", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] returnLogCreate(@RequestBody DeviceReturnRequestDto dto) throws ParseException {
        return returnLogService.saveReturnLog(dto);
    }

    @GetMapping("find/{id}")
    public ReturnLogDetailResponseDto findByLogId(@PathVariable Long id) {
        return returnLogService.findById(id);
    }

    @GetMapping("find/all")
    public List<ReturnLogResponseDto> findAll() {
        return returnLogService.findAll();
    }

    @GetMapping("find/device/{deviceId}")
    public List<ReturnLogResponseDto> findByDeviceId(@PathVariable Long deviceId) {
        return returnLogService.findByDeviceId(deviceId);
    }

    @GetMapping("find/return/fault")
    public List<ReturnLogResponseDto> findByReturnFault() {
        return returnLogService.findByReturnFault();
    }

    @GetMapping("find/return/pass")
    public List<ReturnLogResponseDto> findByReturnPass() {
        return returnLogService.findByReturnPass();
    }

    @GetMapping("delete/all")
    public void deleteAll() {
        returnLogService.deleteAll();
    }
}
