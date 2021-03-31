package com.andersenlab.jwtusertracker.repository;

import com.andersenlab.jwtusertracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name);
}
