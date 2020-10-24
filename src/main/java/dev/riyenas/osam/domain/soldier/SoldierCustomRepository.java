package dev.riyenas.osam.domain.soldier;

import java.util.List;
import java.util.Optional;

public interface SoldierCustomRepository {
    Optional<Soldier> findByServiceNumber(String serviceNumber);
    List<Soldier> findByAdminServiceNumber(String adminServiceNumber);
    List<Soldier> findByAdminSignUpCode(String signUpCode);
    List<Soldier> findAllDesc();
}
