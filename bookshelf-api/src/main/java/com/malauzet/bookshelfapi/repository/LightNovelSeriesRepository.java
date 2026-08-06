package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.LightNovelSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightNovelSeriesRepository extends JpaRepository<LightNovelSeries, Long> {
}
