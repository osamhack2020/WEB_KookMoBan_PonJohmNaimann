package dev.riyenas.osam.domain.rule;

import java.util.Optional;

public interface RuleCustomRepository {
    public Optional<Rule> findByAdminId(Long adminId);
}
