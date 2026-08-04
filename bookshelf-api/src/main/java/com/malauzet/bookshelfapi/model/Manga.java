package com.malauzet.bookshelfapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("MANGA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manga extends Work {

    private String artist;
    private Integer totalPages;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private MangaSeries series;
}
