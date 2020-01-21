package com.kalamin.tododashboard.repository;

import com.kalamin.tododashboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmailEquals(String email);
}
