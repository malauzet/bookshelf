package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.UserAudiobook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAudiobookRepository extends JpaRepository<UserAudiobook, Long> {
}
