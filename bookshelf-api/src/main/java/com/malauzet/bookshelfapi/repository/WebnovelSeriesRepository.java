package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.WebnovelSeries;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Typed repository for {@link WebnovelSeries}. Needed specifically because
 * {@code Webnovel.series} takes a {@code WebnovelSeries} directly — a root repository typed to
 * the abstract {@code Series} would return {@code Optional<Series>}, requiring an unsafe cast to
 * assign it.
 */
public interface WebnovelSeriesRepository extends JpaRepository<WebnovelSeries, Long> {
}
