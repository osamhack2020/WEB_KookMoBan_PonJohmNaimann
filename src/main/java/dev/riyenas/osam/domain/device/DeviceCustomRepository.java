package dev.riyenas.osam.domain.device;

import java.util.Optional;

public interface DeviceCustomRepository {
    Optional<Device> findByGUID(String serialNumber);
    Optional<Device> findByType(String type);
}