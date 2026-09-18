package org.suhas.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhas.auth.domain.User;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
