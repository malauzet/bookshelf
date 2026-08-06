package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.LightNovelNotFoundException;
import com.malauzet.bookshelfapi.exception.LightNovelSeriesNotFoundException;
import com.malauzet.bookshelfapi.model.LightNovel;
import com.malauzet.bookshelfapi.model.LightNovelSeries;
import com.malauzet.bookshelfapi.repository.LightNovelRepository;
import com.malauzet.bookshelfapi.repository.LightNovelSeriesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/light-novels")
@RequiredArgsConstructor
public class LightNovelController {

    private final LightNovelRepository lightNovelRepository;
    private final LightNovelSeriesRepository lightNovelSeriesRepository;

    @PostMapping
    public ResponseEntity<LightNovel> createLightNovel(@RequestBody @Valid LightNovel lightNovel, @RequestParam(required = false) Long seriesId) {

        if (seriesId != null) {
            LightNovelSeries series = lightNovelSeriesRepository.findById(seriesId)
                    .orElseThrow(() -> new LightNovelSeriesNotFoundException("Light Novel series not found with id: " + seriesId));
            lightNovel.setSeries(series);
        }

        LightNovel saved = lightNovelRepository.save(lightNovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<LightNovel>> getAllLightNovels() {
        return ResponseEntity.ok(lightNovelRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LightNovel> getLightNovelById(@PathVariable Long id) {
        LightNovel lightNovel = lightNovelRepository.findById(id)
                .orElseThrow(() -> new LightNovelNotFoundException("Light Novel not found with id: " + id));
        return ResponseEntity.ok(lightNovel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LightNovel> updateLightNovel(@PathVariable Long id,
                                           @RequestBody @Valid LightNovel lightNovel,
                                           @RequestParam(required = false) Long seriesId) {
        LightNovel existing = lightNovelRepository.findById(id)
                .orElseThrow(() -> new LightNovelNotFoundException("Light Novel not found with id: " + id));

        existing.setTitle(lightNovel.getTitle());
        existing.setAuthor(lightNovel.getAuthor());
        existing.setSynopsis(lightNovel.getSynopsis());
        existing.setCoverImageUrl(lightNovel.getCoverImageUrl());
        existing.setPublishedDate(lightNovel.getPublishedDate());
        existing.setTotalChapters(lightNovel.getTotalChapters());
        existing.setVolumeNumber(lightNovel.getVolumeNumber());
        existing.setGenres(lightNovel.getGenres());
        existing.setTotalPages(lightNovel.getTotalPages());
        existing.setArtist(lightNovel.getArtist());

        if (seriesId != null) {
            LightNovelSeries series = lightNovelSeriesRepository.findById(seriesId)
                    .orElseThrow(() -> new LightNovelSeriesNotFoundException("Light Novel series not found with id: " + seriesId));
            existing.setSeries(series);
        }

        LightNovel updated = lightNovelRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLightNovel(@PathVariable Long id) {
        if (!lightNovelRepository.existsById(id)) {
            throw new LightNovelNotFoundException("Light Novel not found with id: " + id);
        }
        lightNovelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
