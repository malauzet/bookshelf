package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.WebnovelNotFoundException;
import com.malauzet.bookshelfapi.exception.WebnovelSeriesNotFoundException;
import com.malauzet.bookshelfapi.model.Webnovel;
import com.malauzet.bookshelfapi.model.WebnovelSeries;
import com.malauzet.bookshelfapi.repository.WebnovelRepository;
import com.malauzet.bookshelfapi.repository.WebnovelSeriesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Full CRUD for {@link Webnovel}. Series attachment goes through a {@code seriesId} query param
 * resolved server-side via {@link WebnovelSeriesRepository}, not a nested {@code series} object
 * in the request body — deserializing a partial nested entity from client JSON would be fragile
 * compared to a typed lookup by id.
 */
@RestController
@RequestMapping("/api/webnovels")
@RequiredArgsConstructor
public class WebnovelController {

    private final WebnovelRepository webnovelRepository;
    private final WebnovelSeriesRepository webnovelSeriesRepository;

    /** @throws WebnovelSeriesNotFoundException if {@code seriesId} is given but doesn't resolve */
    @PostMapping
    public ResponseEntity<Webnovel> createWebnovel(@RequestBody @Valid Webnovel webnovel, @RequestParam(required = false) Long seriesId) {

        if (seriesId != null) {
            WebnovelSeries series = webnovelSeriesRepository.findById(seriesId)
                    .orElseThrow(() -> new WebnovelSeriesNotFoundException("Webnovel series not found with id: " + seriesId));
            webnovel.setSeries(series);
        }

        Webnovel saved = webnovelRepository.save(webnovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Webnovel>> getAllWebnovels() {
        return ResponseEntity.ok(webnovelRepository.findAll());
    }

    /** @throws WebnovelNotFoundException if no webnovel exists with the given id */
    @GetMapping("/{id}")
    public ResponseEntity<Webnovel> getWebnovelById(@PathVariable Long id) {
        Webnovel webnovel = webnovelRepository.findById(id)
                .orElseThrow(() -> new WebnovelNotFoundException("Webnovel not found with id: " + id));
        return ResponseEntity.ok(webnovel);
    }

    /**
     * Full replace: every field in the request body overwrites the existing entity. Omitting
     * {@code seriesId} leaves the current series untouched — there is currently no way to detach
     * an already-assigned series via this endpoint.
     *
     * @throws WebnovelNotFoundException if no webnovel exists with the given id
     * @throws WebnovelSeriesNotFoundException if {@code seriesId} is given but doesn't resolve
     */
    @PutMapping("/{id}")
    public ResponseEntity<Webnovel> updateWebnovel(@PathVariable Long id,
                                           @RequestBody @Valid Webnovel webnovel,
                                           @RequestParam(required = false) Long seriesId) {
        Webnovel existing = webnovelRepository.findById(id)
                .orElseThrow(() -> new WebnovelNotFoundException("Webnovel not found with id: " + id));

        existing.setTitle(webnovel.getTitle());
        existing.setAuthor(webnovel.getAuthor());
        existing.setSynopsis(webnovel.getSynopsis());
        existing.setCoverImageUrl(webnovel.getCoverImageUrl());
        existing.setPublishedDate(webnovel.getPublishedDate());
        existing.setTotalChapters(webnovel.getTotalChapters());
        existing.setVolumeNumber(webnovel.getVolumeNumber());
        existing.setGenres(webnovel.getGenres());

        if (seriesId != null) {
            WebnovelSeries series = webnovelSeriesRepository.findById(seriesId)
                    .orElseThrow(() -> new WebnovelSeriesNotFoundException("Webnovel series not found with id: " + seriesId));
            existing.setSeries(series);
        }

        Webnovel updated = webnovelRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    /** @throws WebnovelNotFoundException if no webnovel exists with the given id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWebnovel(@PathVariable Long id) {
        if (!webnovelRepository.existsById(id)) {
            throw new WebnovelNotFoundException("Webnovel not found with id: " + id);
        }
        webnovelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
