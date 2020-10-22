package dev.riyenas.osam.domain.log;

import java.util.List;
import java.util.Optional;

public interface ReturnLogCustomRepository {
    public List<ReturnLog> findByDeviceIdDesc(Long deviceId);
    public Optional<ReturnLog> findByDeviceIdOne(Long deviceId);
    public List<ReturnLog> findByReturnFaultDesc();
    public List<ReturnLog> findByReturnPassDesc();
    public List<ReturnLog> findAllDesc();
}
