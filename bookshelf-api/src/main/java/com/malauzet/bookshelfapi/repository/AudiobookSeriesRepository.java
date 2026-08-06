package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.AudiobookSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudiobookSeriesRepository extends JpaRepository<AudiobookSeries, Long> {
}
