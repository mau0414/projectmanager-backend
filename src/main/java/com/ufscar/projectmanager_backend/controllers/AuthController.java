package com.ufscar.projectmanager_backend.controllers;

import com.ufscar.projectmanager_backend.models.User;
import com.ufscar.projectmanager_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username is required"));
        }
        User user = userService.login(username);
        return ResponseEntity.ok(Map.of("token", user.getToken()));
    }

    @GetMapping("/teste")
    public ResponseEntity<Map<String, String>> teste(@RequestBody Map<String, String> body) {

        return ResponseEntity.ok(Map.of("teste", body.get("teste")));
    }
}

