package com.fazo.esm.controller;

import com.fazo.esm.entity.Role;
import com.fazo.esm.entity.User;
import com.fazo.esm.payload.AuthenticationResponse;
import com.fazo.esm.payload.RegisterRequest;
import com.fazo.esm.payload.dto.UserDto;
import com.fazo.esm.service.AuthenticationService;
import com.fazo.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        logger.info("Fetching all users");
        List<User> list = userService.findAll();
        logger.info("Fetched all users successfully");
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> insert(@RequestBody UserDto request) {
        logger.info("Registering a new user: {}", request.getUsername());
        RegisterRequest build = RegisterRequest.builder().username(request.getUsername()).password(request.getPassword()).role(Role.USER).build();
        AuthenticationResponse register = authenticationService.register(build);
        logger.info("User registered successfully: {}", request.getUsername());
        return ResponseEntity.ok(register);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        logger.info("Deleting user with ID: {}", id);
        userService.delete(id);
        logger.info("User deleted successfully with ID: {}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }
}
