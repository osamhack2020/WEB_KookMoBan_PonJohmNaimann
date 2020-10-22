package dev.riyenas.osam.domain.log;

import dev.riyenas.osam.domain.device.Device;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

public class ReturnLogRepositoryImpl extends QuerydslRepositorySupport implements ReturnLogCustomRepository{

    public ReturnLogRepositoryImpl() {
        super(Device.class);
    }

    @Override
    public List<ReturnLog> findByDeviceIdDesc(Long deviceId) {

        QReturnLog returnLog = QReturnLog.returnLog;

        return from(returnLog)
                .where(returnLog.device.id.eq(deviceId))
                .orderBy(returnLog.id.desc())
                .fetch();
    }

    @Override
    public Optional<ReturnLog> findByDeviceIdOne(Long deviceId) {

        QReturnLog returnLog = QReturnLog.returnLog;

        return Optional.ofNullable(from(returnLog)
                .where(returnLog.device.id.eq(deviceId))
                .orderBy(returnLog.id.desc())
                .fetchFirst());
    }

    @Override
    public List<ReturnLog> findByReturnFaultDesc() {

        QReturnLog returnLog = QReturnLog.returnLog;

        return from(returnLog)
                .where(returnLog.state.notIn(ReturnState.PASS))
                .orderBy(returnLog.id.desc())
                .fetch();
    }

    @Override
    public List<ReturnLog> findByReturnPassDesc() {

        QReturnLog returnLog = QReturnLog.returnLog;

        return from(returnLog)
                .where(returnLog.state.eq(ReturnState.PASS))
                .orderBy(returnLog.id.desc())
                .fetch();
    }

    @Override
    public List<ReturnLog> findAllDesc() {

        QReturnLog returnLog = QReturnLog.returnLog;

        return from(returnLog)
                .orderBy(returnLog.id.desc())
                .fetch();
    }
}
