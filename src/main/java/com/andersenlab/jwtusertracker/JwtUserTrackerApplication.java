package com.andersenlab.jwtusertracker;

import com.andersenlab.jwtusertracker.model.BaseEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class JwtUserTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtUserTrackerApplication.class, args);
    }

}
