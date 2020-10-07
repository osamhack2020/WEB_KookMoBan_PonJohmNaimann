package dev.riyenas.osam.domain.soldier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldierRepository extends JpaRepository<Soldier, Long>, SoldierCustomRepository {

}
