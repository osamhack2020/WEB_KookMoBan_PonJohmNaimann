package dev.riyenas.osam.domain.log;

import dev.riyenas.osam.domain.device.Device;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ReturnLogRepositoryImpl extends QuerydslRepositorySupport implements ReturnLogCustomRepository{

    public ReturnLogRepositoryImpl() {
        super(Device.class);
    }

    @Override
    public List<ReturnLog> findByDeviceId(Long deviceId) {

        QReturnLog returnLog = QReturnLog.returnLog;

        return from(returnLog)
                .where(returnLog.device.id.eq(deviceId))
                .fetch();
    }

    @Override
    public List<ReturnLog> findByReturnFault() {

        QReturnLog returnLog = QReturnLog.returnLog;

        return from(returnLog)
                .where(returnLog.state.notIn(ReturnState.PASS))
                .fetch();
    }

    @Override
    public List<ReturnLog> findByReturnPass() {

        QReturnLog returnLog = QReturnLog.returnLog;

        return from(returnLog)
                .where(returnLog.state.eq(ReturnState.PASS))
                .fetch();
    }
}
