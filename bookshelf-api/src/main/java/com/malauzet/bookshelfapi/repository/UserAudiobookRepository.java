package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.UserAudiobook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Typed repository for {@link UserAudiobook}, needed for access to its format-specific
 * {@code currentMinutes} field, which isn't exposed on the generic {@code UserWorkRepository}.
 */
public interface UserAudiobookRepository extends JpaRepository<UserAudiobook, Long> {
}
