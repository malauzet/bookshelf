package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.AudiobookSeries;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Typed repository for {@link AudiobookSeries}. Needed specifically because
 * {@code Audiobook.series} takes an {@code AudiobookSeries} directly — a root repository typed to
 * the abstract {@code Series} would return {@code Optional<Series>}, requiring an unsafe cast to
 * assign it.
 */
public interface AudiobookSeriesRepository extends JpaRepository<AudiobookSeries, Long> {
}
