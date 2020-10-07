package dev.riyenas.osam.domain.soldier;

import dev.riyenas.osam.domain.device.Device;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

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
}
