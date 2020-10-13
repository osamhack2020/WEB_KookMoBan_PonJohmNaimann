package dev.riyenas.osam.domain.rule;

import dev.riyenas.osam.domain.device.Device;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class RuleRepositoryImpl extends QuerydslRepositorySupport implements RuleCustomRepository {
    public RuleRepositoryImpl() {
        super(Device.class);
    }

    @Override
    public Optional<Rule> findByAdminId(Long adminId) {
        final QRule rule = QRule.rule;

        return Optional.ofNullable(from(rule)
                .where(rule.admin.id.eq(adminId))
                .fetchOne());
    }
}
