package com.malauzet.bookshelfapi.model;

import jakarta.persistence.*;
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

    private String title;
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
    private Integer rating; // 1-5, nullable until finished
}