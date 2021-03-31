package com.andersenlab.jwtusertracker.repository;

import com.andersenlab.jwtusertracker.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String name);
}