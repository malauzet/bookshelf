package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.dto.UserWorkUpdateRequest;
import com.malauzet.bookshelfapi.exception.UserNotFoundException;
import com.malauzet.bookshelfapi.exception.UserWorkNotFoundException;
import com.malauzet.bookshelfapi.model.ReadingStatus;
import com.malauzet.bookshelfapi.model.User;
import com.malauzet.bookshelfapi.model.UserAudiobook;
import com.malauzet.bookshelfapi.model.UserBook;
import com.malauzet.bookshelfapi.model.UserLightNovel;
import com.malauzet.bookshelfapi.model.UserManga;
import com.malauzet.bookshelfapi.model.UserWork;
import com.malauzet.bookshelfapi.repository.UserRepository;
import com.malauzet.bookshelfapi.repository.UserWorkRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}")
@RequiredArgsConstructor
public class UserWorkController {

    private final UserRepository userRepository;
    private final UserWorkRepository userWorkRepository;

    @GetMapping("/user-works")
    public ResponseEntity<List<UserWork>> getUserWorksByStatus(@PathVariable Long userId,
                                                                @RequestParam ReadingStatus status) {
        User user = getUser(userId);
        return ResponseEntity.ok(userWorkRepository.findByUserAndStatus(user, status));
    }

    @PatchMapping("/user-works/{id}")
    public ResponseEntity<UserWork> updateUserWork(@PathVariable Long userId, @PathVariable Long id,
                                                    @RequestBody @Valid UserWorkUpdateRequest request) {
        UserWork existing = getOwnedUserWork(userId, id);

        if (request.status() != null) {
            existing.setStatus(request.status());
        }
        if (request.rating() != null) {
            existing.setRating(request.rating());
        }
        if (request.currentChapter() != null) {
            existing.setCurrentChapter(request.currentChapter());
        }

        if (existing instanceof UserBook userBook && request.currentPage() != null) {
            userBook.setCurrentPage(request.currentPage());
        } else if (existing instanceof UserManga userManga && request.currentPage() != null) {
            userManga.setCurrentPage(request.currentPage());
        } else if (existing instanceof UserLightNovel userLightNovel && request.currentPage() != null) {
            userLightNovel.setCurrentPage(request.currentPage());
        } else if (existing instanceof UserAudiobook userAudiobook && request.currentMinutes() != null) {
            userAudiobook.setCurrentMinutes(request.currentMinutes());
        }

        UserWork updated = userWorkRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/user-works/{id}")
    public ResponseEntity<Void> deleteUserWork(@PathVariable Long userId, @PathVariable Long id) {
        UserWork existing = getOwnedUserWork(userId, id);
        userWorkRepository.delete(existing);
        return ResponseEntity.noContent().build();
    }

    private UserWork getOwnedUserWork(Long userId, Long id) {
        UserWork existing = userWorkRepository.findById(id)
                .orElseThrow(() -> new UserWorkNotFoundException("User work not found with id: " + id));
        if (!existing.getUser().getId().equals(userId)) {
            throw new UserWorkNotFoundException("User work not found with id: " + id);
        }
        return existing;
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }
}