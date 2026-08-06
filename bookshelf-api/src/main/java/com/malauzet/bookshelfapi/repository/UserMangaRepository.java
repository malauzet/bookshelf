package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.UserManga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMangaRepository extends JpaRepository<UserManga,Long> {
}
