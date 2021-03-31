package com.andersenlab.jwtusertracker.service.impl;

import com.andersenlab.jwtusertracker.model.Role;
import com.andersenlab.jwtusertracker.model.Status;
import com.andersenlab.jwtusertracker.model.UserEntity;
import com.andersenlab.jwtusertracker.repository.RoleRepository;
import com.andersenlab.jwtusertracker.repository.UserEntityRepository;
import com.andersenlab.jwtusertracker.service.UserEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserEntityServiceImpl implements UserEntityService {
    private final UserEntityRepository userEntityRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserEntityServiceImpl(UserEntityRepository userEntityRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity register(UserEntity user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        UserEntity registeredUser = userEntityRepository.save(user);
        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> result = userEntityRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public UserEntity findByUsername(String username) {
        UserEntity result = userEntityRepository.findByUserName(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public UserEntity findById(Long id) {
        UserEntity entity = userEntityRepository.findById(id).orElse(null);
        if (entity == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN findById - user: {} found by id: {}", entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        userEntityRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }
}
