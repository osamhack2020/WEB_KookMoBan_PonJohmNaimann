package dev.riyenas.osam.domain.soldier;

import java.util.Optional;

public interface SoldierCustomRepository {
    Optional<Soldier> findByServiceNumber(String serviceNumber);
}
