package dev.riyenas.osam.domain.device;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class DeviceRepositoryImpl extends QuerydslRepositorySupport implements DeviceCustomRepository {

    public DeviceRepositoryImpl() {
        super(Device.class);
    }
}