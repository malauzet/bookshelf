package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.DuplicateTrackingException;
import com.malauzet.bookshelfapi.exception.MangaNotFoundException;
import com.malauzet.bookshelfapi.exception.UserNotFoundException;
import com.malauzet.bookshelfapi.model.Manga;
import com.malauzet.bookshelfapi.model.User;
import com.malauzet.bookshelfapi.model.UserManga;
import com.malauzet.bookshelfapi.repository.MangaRepository;
import com.malauzet.bookshelfapi.repository.UserMangaRepository;
import com.malauzet.bookshelfapi.repository.UserRepository;
import com.malauzet.bookshelfapi.repository.UserWorkRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Owns only the {@code Manga}-specific "start tracking" endpoint — everything else on an
 * already-tracked {@link UserManga} (get/patch/delete) is handled generically by
 * {@link UserWorkController} (see its class Javadoc for why).
 */
@RestController
@RequestMapping("/api/users/{userId}")
@RequiredArgsConstructor
public class UserMangaController {

    private final UserRepository userRepository;
    private final MangaRepository mangaRepository;
    private final UserWorkRepository userWorkRepository;
    private final UserMangaRepository userMangaRepository;

    /**
     * @throws UserNotFoundException if {@code userId} doesn't resolve
     * @throws MangaNotFoundException if {@code mangaId} doesn't resolve
     * @throws DuplicateTrackingException if {@code userId} is already tracking this manga
     */
    @PostMapping("/mangas/{mangaId}")
    public ResponseEntity<UserManga> trackManga(@PathVariable Long userId, @PathVariable Long mangaId,
                                                          @RequestBody @Valid UserManga userManga) {

        User user = getUser(userId);
        Manga manga = mangaRepository.findById(mangaId)
                .orElseThrow(() -> new MangaNotFoundException("Manga not found with id: " + mangaId));

        if (userWorkRepository.existsByUserAndWork(user, manga)) {
            throw new DuplicateTrackingException("User " + userId + " is already tracking work " + mangaId);
        }

        userManga.setUser(user);
        userManga.setWork(manga);

        UserManga saved = userMangaRepository.save(userManga);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }
}
