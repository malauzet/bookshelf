package com.malauzet.bookshelfapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "work_type", length = 20)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    @Column(columnDefinition = "TEXT")
    private String synopsis;
    private String coverImageUrl;
    private LocalDate publishedDate;
    private Integer totalChapters;
    private Integer volumeNumber;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "work_genre", joinColumns = @JoinColumn(name = "work_id"))
    @Column(name = "genre")
    private Set<Genre> genres;
}
