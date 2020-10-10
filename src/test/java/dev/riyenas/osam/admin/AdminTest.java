package dev.riyenas.osam.admin;

import dev.riyenas.osam.domain.admin.Admin;
import dev.riyenas.osam.domain.admin.AdminRepository;
import dev.riyenas.osam.domain.soldier.Soldier;
import dev.riyenas.osam.domain.soldier.SoldierRepository;
import dev.riyenas.osam.service.SoldierService;
import dev.riyenas.osam.web.dto.app.SoldierSignUpRequestDto;
import dev.riyenas.osam.web.dto.soldier.SoldierResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {
    private static final Logger log = LoggerFactory.getLogger(AdminTest.class);

    @Autowired
    private SoldierService soldierService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SoldierRepository soldierRepository;

    @Test
    @Transactional
    public void signUpCode() {
        SoldierSignUpRequestDto dto = SoldierSignUpRequestDto.builder()
                .signUpCode("99999999")
                .serviceNumber("19-123456")
                .serialNumber("S/N123456")
                .build();

        Admin admin = Admin.builder()
                .name("홍길동")
                .signUpCode("99999999")
                .build();

        adminRepository.save(admin);

        soldierService.createSoldier(dto);

        log.info(soldierRepository.findAll().stream().map(SoldierResponseDto::new).collect(Collectors.toList()).toString());
    }

    @Test
    @Transactional(readOnly = true)
    public void findByServiceNumber() {
        List<Soldier> soldiers = soldierRepository.findByAdminServiceNumber("20-123456");
    }
}
