package com.malauzet.bookshelfapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@DiscriminatorValue("BOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Work {

    private Integer totalPages;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private BookSeries series;
}