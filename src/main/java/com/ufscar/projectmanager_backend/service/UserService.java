package com.ufscar.projectmanager_backend.service;

import com.ufscar.projectmanager_backend.models.User;
import com.ufscar.projectmanager_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String username) {
        return userRepository.findByName(username)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setName(username);
                    newUser.setToken(UUID.randomUUID().toString());
                    return userRepository.save(newUser);
                });
    }

    public Optional<User> findByToken(String token) {
        return userRepository.findByToken(token);
    }
}
