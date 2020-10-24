package dev.riyenas.osam.domain.device;

import java.util.List;
import java.util.Optional;

public interface DeviceCustomRepository {
    Optional<Device> findByGUID(String serialNumber);
    Optional<Device> findByType(String type);
    List<Device> findAllDesc();
}