package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.LightNovelNotFoundException;
import com.malauzet.bookshelfapi.exception.DuplicateTrackingException;
import com.malauzet.bookshelfapi.exception.UserNotFoundException;
import com.malauzet.bookshelfapi.model.LightNovel;
import com.malauzet.bookshelfapi.model.User;
import com.malauzet.bookshelfapi.model.UserLightNovel;
import com.malauzet.bookshelfapi.repository.LightNovelRepository;
import com.malauzet.bookshelfapi.repository.UserLightNovelRepository;
import com.malauzet.bookshelfapi.repository.UserRepository;
import com.malauzet.bookshelfapi.repository.UserWorkRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Owns only the {@code LightNovel}-specific "start tracking" endpoint — everything else on an
 * already-tracked {@link UserLightNovel} (get/patch/delete) is handled generically by
 * {@link UserWorkController} (see its class Javadoc for why).
 */
@RestController
@RequestMapping("/api/users/{userId}")
@RequiredArgsConstructor
public class UserLightNovelController {

    private final UserRepository userRepository;
    private final LightNovelRepository lightNovelRepository;
    private final UserWorkRepository userWorkRepository;
    private final UserLightNovelRepository userLightNovelRepository;

    /**
     * @throws UserNotFoundException if {@code userId} doesn't resolve
     * @throws LightNovelNotFoundException if {@code lightNovelId} doesn't resolve
     * @throws DuplicateTrackingException if {@code userId} is already tracking this light novel
     */
    @PostMapping("/light-novels/{lightNovelId}")
    public ResponseEntity<UserLightNovel> trackLightNovel(@PathVariable Long userId, @PathVariable Long lightNovelId,
                                                           @RequestBody @Valid UserLightNovel userLightNovel) {

        User user = getUser(userId);
        LightNovel lightNovel = lightNovelRepository.findById(lightNovelId)
                .orElseThrow(() -> new LightNovelNotFoundException("LightNovel not found with id: " + lightNovelId));

        if (userWorkRepository.existsByUserAndWork(user, lightNovel)) {
            throw new DuplicateTrackingException("User " + userId + " is already tracking work " + lightNovelId);
        }

        userLightNovel.setUser(user);
        userLightNovel.setWork(lightNovel);

        UserLightNovel saved = userLightNovelRepository.save(userLightNovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }
}
