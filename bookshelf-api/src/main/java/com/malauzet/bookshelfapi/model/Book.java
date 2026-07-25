package com.malauzet.bookshelfapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    @Enumerated(EnumType.STRING)
    private ReadingStatus status;
    private String coverImageUrl;
    @Column(columnDefinition = "TEXT")
    private String synopsis;
    private String genre;
    private String series;
    private Integer currentChapter;
    private Integer totalChapters;
    private LocalDate publishedDate;
    private String language;
    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private Integer rating;
}