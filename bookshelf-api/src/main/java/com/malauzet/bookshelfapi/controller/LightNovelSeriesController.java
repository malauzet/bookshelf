package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.LightNovelSeriesNotFoundException;
import com.malauzet.bookshelfapi.model.LightNovelSeries;
import com.malauzet.bookshelfapi.repository.LightNovelSeriesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Full CRUD for {@link LightNovelSeries}. */
@RestController
@RequestMapping("/api/light-novel-series")
@RequiredArgsConstructor
public class LightNovelSeriesController {

    private final LightNovelSeriesRepository lightNovelSeriesRepository;

    @PostMapping
    public ResponseEntity<LightNovelSeries> createLightNovelSeries(@RequestBody @Valid LightNovelSeries lightNovelSeries) {
        LightNovelSeries saved = lightNovelSeriesRepository.save(lightNovelSeries);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<LightNovelSeries>> getAllLightNovelSeries() {
        return ResponseEntity.ok(lightNovelSeriesRepository.findAll());
    }

    /** @throws LightNovelSeriesNotFoundException if no series exists with the given id */
    @GetMapping("/{id}")
    public ResponseEntity<LightNovelSeries> getLightNovelSeriesById(@PathVariable Long id) {
        LightNovelSeries series = lightNovelSeriesRepository.findById(id)
                .orElseThrow(() -> new LightNovelSeriesNotFoundException("Light Novel series not found with id: " + id));
        return ResponseEntity.ok(series);
    }

    /**
     * Full replace: every field in the request body overwrites the existing entity.
     *
     * @throws LightNovelSeriesNotFoundException if no series exists with the given id
     */
    @PutMapping("/{id}")
    public ResponseEntity<LightNovelSeries> updateLightNovelSeries(@PathVariable Long id, @RequestBody @Valid LightNovelSeries lightNovelSeries) {
        LightNovelSeries existing = lightNovelSeriesRepository.findById(id)
                .orElseThrow(() -> new LightNovelSeriesNotFoundException("Light Novel series not found with id: " + id));

        existing.setName(lightNovelSeries.getName());
        existing.setAuthor(lightNovelSeries.getAuthor());
        existing.setTotalVolumes(lightNovelSeries.getTotalVolumes());

        LightNovelSeries updated = lightNovelSeriesRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    /** @throws LightNovelSeriesNotFoundException if no series exists with the given id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLightNovelSeries(@PathVariable Long id) {
        if (!lightNovelSeriesRepository.existsById(id)) {
            throw new LightNovelSeriesNotFoundException("Light Novel series not found with id: " + id);
        }
        lightNovelSeriesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
