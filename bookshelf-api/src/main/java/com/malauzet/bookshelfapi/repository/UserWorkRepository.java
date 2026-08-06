package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.ReadingStatus;
import com.malauzet.bookshelfapi.model.User;
import com.malauzet.bookshelfapi.model.UserWork;
import com.malauzet.bookshelfapi.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Root repository over the generic {@link UserWork} base type, for queries that need to work
 * across every tracked format at once — the counterpart to the per-format repositories
 * ({@link UserBookRepository} etc.) used where a subclass-only field is involved. Backs the
 * shared {@code UserWorkController} endpoints (get/patch/delete by id, list by status).
 */
public interface UserWorkRepository extends JpaRepository<UserWork, Long> {

    /** Powers the UI's per-status tabs (Reading/Plan to Read/...), regardless of format. */
    List<UserWork> findByUserAndStatus(User user, ReadingStatus status);

    /** Mirrors the {@code UNIQUE(user_id, work_id)} DB constraint at the application level. */
    boolean existsByUserAndWork(User user, Work work);
}
