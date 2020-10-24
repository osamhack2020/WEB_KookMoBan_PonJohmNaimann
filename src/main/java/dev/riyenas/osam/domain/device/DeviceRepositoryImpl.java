package dev.riyenas.osam.domain.device;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

public class DeviceRepositoryImpl extends QuerydslRepositorySupport implements DeviceCustomRepository {

    public DeviceRepositoryImpl() {
        super(Device.class);
    }

    @Override
    public Optional<Device> findByGUID(String guid) {
        final QDevice device = QDevice.device;

        return Optional.ofNullable(from(device)
                .where(device.guid.eq(guid))
                .fetchOne());
    }

    @Override
    public Optional<Device> findByType(String type) {
        final QDevice device = QDevice.device;

        return Optional.ofNullable(from(device)
                .where(device.type.eq(type))
                .fetchOne());
    }

    @Override
    public List<Device> findAllDesc() {
        final QDevice device = QDevice.device;

        return from(device)
                .orderBy(device.id.desc())
                .fetch();
    }
}