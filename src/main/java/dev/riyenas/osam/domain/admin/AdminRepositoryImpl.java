package dev.riyenas.osam.domain.admin;

import dev.riyenas.osam.domain.device.Device;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class AdminRepositoryImpl extends QuerydslRepositorySupport implements AdminCustomRepository {

    public AdminRepositoryImpl() {
        super(Device.class);
    }

    @Override
    public Optional<Admin> findBySignUpCode(String signUpCode) {
        final QAdmin admin = QAdmin.admin;

        return Optional.ofNullable(from(admin)
                .where(admin.signUpCode.eq(signUpCode))
                .fetchOne());
    }
}
