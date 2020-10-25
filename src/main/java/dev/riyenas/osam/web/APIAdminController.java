package dev.riyenas.osam.web;

import dev.riyenas.osam.service.AdminService;
import dev.riyenas.osam.web.dto.admin.AdminResponseDto;
import dev.riyenas.osam.web.dto.admin.AdminRuleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/")
public class APIAdminController {

    private final AdminService adminService;

    @GetMapping("find/all")
    public List<AdminRuleResponseDto> findAll() {
        return adminService.findAll();
    }

    @GetMapping("find/signUpCode/{signUpCode}")
    public AdminResponseDto findSignUpCode(@PathVariable String signUpCode) {
        return adminService.findBySignUpCode(signUpCode);
    }
}
