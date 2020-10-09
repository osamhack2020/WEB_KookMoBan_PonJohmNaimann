package dev.riyenas.osam.domain.admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>, AdminCustomRepository{

}
