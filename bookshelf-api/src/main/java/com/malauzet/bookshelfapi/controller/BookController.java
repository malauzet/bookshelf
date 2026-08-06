package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.BookNotFoundException;
import com.malauzet.bookshelfapi.exception.BookSeriesNotFoundException;
import com.malauzet.bookshelfapi.model.Book;
import com.malauzet.bookshelfapi.model.BookSeries;
import com.malauzet.bookshelfapi.repository.BookRepository;
import com.malauzet.bookshelfapi.repository.BookSeriesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Full CRUD for {@link Book}. Series attachment goes through a {@code seriesId} query param
 * resolved server-side via {@link BookSeriesRepository}, not a nested {@code series} object in
 * the request body — deserializing a partial nested entity from client JSON would be fragile
 * compared to a typed lookup by id.
 */
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final BookSeriesRepository bookSeriesRepository;

    /** @throws BookSeriesNotFoundException if {@code seriesId} is given but doesn't resolve */
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book, @RequestParam(required = false) Long seriesId) {

        if (seriesId != null) {
            BookSeries series = bookSeriesRepository.findById(seriesId)
                    .orElseThrow(() -> new BookSeriesNotFoundException("Book series not found with id: " + seriesId));
            book.setSeries(series);
        }

        Book saved = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    /** @throws BookNotFoundException if no book exists with the given id */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return ResponseEntity.ok(book);
    }

    /**
     * Full replace: every field in the request body overwrites the existing entity. Omitting
     * {@code seriesId} leaves the current series untouched — there is currently no way to detach
     * an already-assigned series via this endpoint.
     *
     * @throws BookNotFoundException if no book exists with the given id
     * @throws BookSeriesNotFoundException if {@code seriesId} is given but doesn't resolve
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                                           @RequestBody @Valid Book book,
                                           @RequestParam(required = false) Long seriesId) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setSynopsis(book.getSynopsis());
        existing.setCoverImageUrl(book.getCoverImageUrl());
        existing.setPublishedDate(book.getPublishedDate());
        existing.setTotalChapters(book.getTotalChapters());
        existing.setVolumeNumber(book.getVolumeNumber());
        existing.setGenres(book.getGenres());
        existing.setTotalPages(book.getTotalPages());

        if (seriesId != null) {
            BookSeries series = bookSeriesRepository.findById(seriesId)
                    .orElseThrow(() -> new BookSeriesNotFoundException("Book series not found with id: " + seriesId));
            existing.setSeries(series);
        }

        Book updated = bookRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    /** @throws BookNotFoundException if no book exists with the given id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
