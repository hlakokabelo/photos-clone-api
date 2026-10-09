package com.jetbrains.kabelo.photos.clone.api.web;

import java.util.Map;
import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import com.jetbrains.kabelo.photos.clone.api.dto.*;
import com.jetbrains.kabelo.photos.clone.api.model.User;
import com.jetbrains.kabelo.photos.clone.api.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

        private final AuthService authService;

        public AuthController(AuthService authService) {
                this.authService = authService;
        }

        @PostMapping("/register")
        public ResponseEntity<Map<String, Object>> register(
                        @Valid // verifies against constraints in RegisterRequest class
                        @RequestBody // Reads the request body and converts it into a Java object.
                        RegisterRequest request) {

                User user = authService.register(request);

                URI location = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/users/{id}")
                                .buildAndExpand(user.getId())
                                .toUri();

                return ResponseEntity
                                .created(location)
                                .body(Map.of(
                                                "id", user.getId(),
                                                "username", user.getUsername(),
                                                "message", "Account created successfully"));
        }

        @PostMapping("/login")
        public ResponseEntity<Map<String, String>> login(
                        @Valid @RequestBody LoginRequest request) {

                String token = authService.login(request);

                return ResponseEntity.ok(Map.of(
                                "accessToken", token,
                                "tokenType", "Bearer"));
        }
}