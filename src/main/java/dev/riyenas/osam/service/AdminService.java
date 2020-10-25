package dev.riyenas.osam.service;

import dev.riyenas.osam.domain.admin.AdminRepository;
import dev.riyenas.osam.web.dto.admin.AdminResponseDto;
import dev.riyenas.osam.web.dto.admin.AdminRuleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public List<AdminRuleResponseDto> findAll() {
        return adminRepository.findAll().stream()
                .map(AdminRuleResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public AdminResponseDto findBySignUpCode(String signUpCode) {
        return new AdminResponseDto(adminRepository.findBySignUpCode(signUpCode).orElseThrow(() ->
            new IllegalArgumentException("관리자를 찾을수 없습니다.")
        ));
    }
}
