package com.malauzet.bookshelfapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    @Column(columnDefinition = "TEXT")
    private String synopsis;
    private String coverImageUrl;
    private LocalDate publishedDate;

    @Positive(message = "Total chapters must be greater than 0")
    private Integer totalChapters;

    @Positive(message = "Volume number must be greater than 0")
    private Integer volumeNumber;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "work_genre", joinColumns = @JoinColumn(name = "work_id"))
    @Column(name = "genre")
    private Set<Genre> genres;
}
