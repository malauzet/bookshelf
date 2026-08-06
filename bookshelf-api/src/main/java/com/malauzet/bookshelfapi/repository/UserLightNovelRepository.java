package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.UserLightNovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLightNovelRepository extends JpaRepository<UserLightNovel, Long> {
}
