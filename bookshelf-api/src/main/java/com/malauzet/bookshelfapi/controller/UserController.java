package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.DuplicateUsernameException;
import com.malauzet.bookshelfapi.exception.UserNotFoundException;
import com.malauzet.bookshelfapi.model.User;
import com.malauzet.bookshelfapi.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody @Valid User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateUsernameException("Username already taken: " + user.getUsername());
        }

        User savedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return ResponseEntity.ok(user);
    }
}
