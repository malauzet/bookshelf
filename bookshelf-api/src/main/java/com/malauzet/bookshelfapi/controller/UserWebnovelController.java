package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.WebnovelNotFoundException;
import com.malauzet.bookshelfapi.exception.DuplicateTrackingException;
import com.malauzet.bookshelfapi.exception.UserNotFoundException;
import com.malauzet.bookshelfapi.model.User;
import com.malauzet.bookshelfapi.model.UserWebnovel;
import com.malauzet.bookshelfapi.model.Webnovel;
import com.malauzet.bookshelfapi.repository.WebnovelRepository;
import com.malauzet.bookshelfapi.repository.UserWebnovelRepository;
import com.malauzet.bookshelfapi.repository.UserRepository;
import com.malauzet.bookshelfapi.repository.UserWorkRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}")
@RequiredArgsConstructor
public class UserWebnovelController {

    private final UserRepository userRepository;
    private final WebnovelRepository webnovelRepository;
    private final UserWorkRepository userWorkRepository;
    private final UserWebnovelRepository userWebnovelRepository;

    @PostMapping("/webnovels/{webnovelId}")
    public ResponseEntity<UserWebnovel> trackWebnovel(@PathVariable Long userId, @PathVariable Long webnovelId,
                                              @RequestBody @Valid UserWebnovel userWebnovel) {

        User user = getUser(userId);
        Webnovel webnovel = webnovelRepository.findById(webnovelId)
                .orElseThrow(() -> new WebnovelNotFoundException("Webnovel not found with id: " + webnovelId));

        if (userWorkRepository.existsByUserAndWork(user, webnovel)) {
            throw new DuplicateTrackingException("User " + userId + " is already tracking work " + webnovelId);
        }

        userWebnovel.setUser(user);
        userWebnovel.setWork(webnovel);

        UserWebnovel saved = userWebnovelRepository.save(userWebnovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }
}