package com.example.emailtemplate.infrastructure.domain.sql.repository;

import com.example.emailtemplate.infrastructure.domain.sql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
