package dev.riyenas.osam.domain.rule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long>, RuleCustomRepository {
}
