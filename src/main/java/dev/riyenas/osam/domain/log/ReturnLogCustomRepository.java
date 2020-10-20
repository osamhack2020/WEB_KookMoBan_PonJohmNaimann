package dev.riyenas.osam.domain.log;

import java.util.List;

public interface ReturnLogCustomRepository {
    public List<ReturnLog> findByDeviceId(Long deviceId);
    public List<ReturnLog> findByReturnFault();
    public List<ReturnLog> findByReturnPass();
}
