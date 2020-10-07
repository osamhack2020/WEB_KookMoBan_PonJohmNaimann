package dev.riyenas.osam.domain.device;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long>, DeviceCustomRepository {

}