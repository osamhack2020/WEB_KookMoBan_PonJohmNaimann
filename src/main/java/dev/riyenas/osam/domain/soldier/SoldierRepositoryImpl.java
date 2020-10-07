package dev.riyenas.osam.domain.soldier;

import dev.riyenas.osam.domain.device.Device;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class SoldierRepositoryImpl extends QuerydslRepositorySupport implements SoldierCustomRepository {

    public SoldierRepositoryImpl() {
        super(Device.class);
    }

}
