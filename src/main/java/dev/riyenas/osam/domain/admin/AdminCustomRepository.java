package dev.riyenas.osam.domain.admin;

import java.util.Optional;

public interface AdminCustomRepository {

    Optional<Admin> findBySignUpCode(String signUpCode);

}
