package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.DuplicateTrackingException;
import com.malauzet.bookshelfapi.exception.AudiobookNotFoundException;
import com.malauzet.bookshelfapi.exception.UserNotFoundException;
import com.malauzet.bookshelfapi.model.Audiobook;
import com.malauzet.bookshelfapi.model.User;
import com.malauzet.bookshelfapi.model.UserAudiobook;
import com.malauzet.bookshelfapi.repository.AudiobookRepository;
import com.malauzet.bookshelfapi.repository.UserAudiobookRepository;
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
public class UserAudiobookController {

    private final UserRepository userRepository;
    private final AudiobookRepository audiobookRepository;
    private final UserWorkRepository userWorkRepository;
    private final UserAudiobookRepository userAudiobookRepository;

    @PostMapping("/audiobooks/{audiobookId}")
    public ResponseEntity<UserAudiobook> trackAudiobook(@PathVariable Long userId, @PathVariable Long audiobookId,
                                                @RequestBody @Valid UserAudiobook userAudiobook) {

        User user = getUser(userId);
        Audiobook audiobook = audiobookRepository.findById(audiobookId)
                .orElseThrow(() -> new AudiobookNotFoundException("Audiobook not found with id: " + audiobookId));

        if (userWorkRepository.existsByUserAndWork(user, audiobook)) {
            throw new DuplicateTrackingException("User " + userId + " is already tracking work " + audiobookId);
        }

        userAudiobook.setUser(user);
        userAudiobook.setWork(audiobook);

        UserAudiobook saved = userAudiobookRepository.save(userAudiobook);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }
}
