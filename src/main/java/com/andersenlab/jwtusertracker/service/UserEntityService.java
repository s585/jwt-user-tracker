package com.andersenlab.jwtusertracker.service;

import com.andersenlab.jwtusertracker.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserEntityService {
    UserEntity register(UserEntity user);

    List<UserEntity> getAll();

    UserEntity findByUsername(String username);

    UserEntity findById(Long id);

    void delete(Long id);
}
