package com.malauzet.bookshelfapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
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

    @Positive(message = "Total pages must be greater than 0")
    private Integer totalPages;

    @ManyToOne
    @JoinColumn(name = "series_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BookSeries series;
}