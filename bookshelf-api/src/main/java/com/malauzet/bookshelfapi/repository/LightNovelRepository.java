package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.LightNovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightNovelRepository extends JpaRepository<LightNovel, Long> {
}
