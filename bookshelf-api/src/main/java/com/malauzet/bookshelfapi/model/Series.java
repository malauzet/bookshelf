package com.malauzet.bookshelfapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "series_type", length = 20)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Series' name is required")
    private String name;
    @NotBlank(message = "Author is required")
    private String author;
    private Integer totalVolumes;
}
