package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.LightNovelSeries;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Typed repository for {@link LightNovelSeries}. Needed specifically because
 * {@code LightNovel.series} takes a {@code LightNovelSeries} directly — a root repository typed
 * to the abstract {@code Series} would return {@code Optional<Series>}, requiring an unsafe cast
 * to assign it.
 */
public interface LightNovelSeriesRepository extends JpaRepository<LightNovelSeries, Long> {
}
