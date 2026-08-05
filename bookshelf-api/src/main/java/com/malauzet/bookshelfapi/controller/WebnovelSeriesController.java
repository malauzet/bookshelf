package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.WebnovelSeriesNotFoundException;
import com.malauzet.bookshelfapi.model.WebnovelSeries;
import com.malauzet.bookshelfapi.repository.WebnovelSeriesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/webnovel-series")
@RequiredArgsConstructor
public class WebnovelSeriesController {
    
    private final WebnovelSeriesRepository webnovelSeriesRepository;

    @PostMapping
    public ResponseEntity<WebnovelSeries> createWebnovelSeries(@RequestBody @Valid WebnovelSeries webnovelSeries) {
        WebnovelSeries saved = webnovelSeriesRepository.save(webnovelSeries);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<WebnovelSeries>> getAllWebnovelSeries() {
        return ResponseEntity.ok(webnovelSeriesRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebnovelSeries> getWebnovelSeriesById(@PathVariable Long id) {
        WebnovelSeries series = webnovelSeriesRepository.findById(id)
                .orElseThrow(() -> new WebnovelSeriesNotFoundException("Webnovel series not found with id: " + id));
        return ResponseEntity.ok(series);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebnovelSeries> updateWebnovelSeries(@PathVariable Long id, @RequestBody @Valid WebnovelSeries webnovelSeries) {
        WebnovelSeries existing = webnovelSeriesRepository.findById(id)
                .orElseThrow(() -> new WebnovelSeriesNotFoundException("Webnovel series not found with id: " + id));

        existing.setName(webnovelSeries.getName());
        existing.setAuthor(webnovelSeries.getAuthor());
        existing.setTotalVolumes(webnovelSeries.getTotalVolumes());

        WebnovelSeries updated = webnovelSeriesRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWebnovelSeries(@PathVariable Long id) {
        if (!webnovelSeriesRepository.existsById(id)) {
            throw new WebnovelSeriesNotFoundException("Webnovel series not found with id: " + id);
        }
        webnovelSeriesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
