package com.malauzet.bookshelfapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("LIGHT_NOVEL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLightNovel extends UserWork {

    @Positive(message = "Current page must be greater than 0")
    private Integer currentPage;
}
