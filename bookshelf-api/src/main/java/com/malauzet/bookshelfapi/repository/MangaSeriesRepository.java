package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.MangaSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaSeriesRepository extends JpaRepository<MangaSeries, Long> {
}
