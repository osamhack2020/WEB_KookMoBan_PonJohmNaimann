package dev.riyenas.osam.domain.soldier;

import dev.riyenas.osam.domain.device.Device;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

public class SoldierRepositoryImpl extends QuerydslRepositorySupport implements SoldierCustomRepository {

    public SoldierRepositoryImpl() {
        super(Device.class);
    }

    @Override
    public Optional<Soldier> findByServiceNumber(String serviceNumber) {
        final QSoldier soldier = QSoldier.soldier;

        return Optional.ofNullable(from(soldier)
                .where(soldier.serviceNumber.eq(serviceNumber))
                .fetchOne());
    }

    @Override
    public List<Soldier> findByAdminServiceNumber(String adminServiceNumber) {
        final QSoldier soldier = QSoldier.soldier;

        return from(soldier)
                .where(soldier.admin.serviceNumber.eq(adminServiceNumber))
                .fetch();
    }

    @Override
    public List<Soldier> findByAdminSignUpCode(String signUpCode) {
        final QSoldier soldier = QSoldier.soldier;

        return from(soldier)
                .where(soldier.admin.signUpCode.eq(signUpCode))
                .fetch();
    }


}
