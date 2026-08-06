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

/**
 * Operations on the generic {@link UserWork} base type — get/patch/delete for an already-tracked
 * work, regardless of format. These belong in one shared controller rather than duplicated per
 * format: they never touch a subclass-only field, and Spring MVC doesn't dispatch routes on
 * {@code @RequestBody} type, so per-format duplicates of these same paths previously collided
 * with an {@code Ambiguous mapping} startup crash the moment a second format controller existed.
 * Only the {@code POST} "track" endpoint (which must construct a concrete {@code UserBook}/
 * {@code UserWebnovel}/... subtype) stays in each per-format controller.
 */
@RestController
@RequestMapping("/api/users/{userId}")
@RequiredArgsConstructor
public class UserWorkController {

    private final UserRepository userRepository;
    private final UserWorkRepository userWorkRepository;

    /** Powers the UI's per-status tabs (Reading/Plan to Read/...), regardless of format. */
    @GetMapping("/user-works")
    public ResponseEntity<List<UserWork>> getUserWorksByStatus(@PathVariable Long userId,
                                                                @RequestParam ReadingStatus status) {
        User user = getUser(userId);
        return ResponseEntity.ok(userWorkRepository.findByUserAndStatus(user, status));
    }

    /**
     * Partial update: only non-null fields in {@code request} are applied. {@code currentPage}/
     * {@code currentMinutes} are applied conditionally, based on which concrete subtype
     * {@code existing} actually is — a field that doesn't apply to the resolved subtype (e.g.
     * {@code currentPage} sent for a {@code UserWebnovel}) is silently ignored.
     *
     * @throws UserWorkNotFoundException if the id doesn't exist or doesn't belong to {@code userId}
     */
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

    /** @throws UserWorkNotFoundException if the id doesn't exist or doesn't belong to {@code userId} */
    @DeleteMapping("/user-works/{id}")
    public ResponseEntity<Void> deleteUserWork(@PathVariable Long userId, @PathVariable Long id) {
        UserWork existing = getOwnedUserWork(userId, id);
        userWorkRepository.delete(existing);
        return ResponseEntity.noContent().build();
    }

    /**
     * Fetches the {@code UserWork} and verifies it actually belongs to {@code userId}. A mismatch
     * is masked as the same {@code 404} used for "doesn't exist at all" — a caller shouldn't be
     * able to distinguish "not yours" from "no such resource".
     */
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