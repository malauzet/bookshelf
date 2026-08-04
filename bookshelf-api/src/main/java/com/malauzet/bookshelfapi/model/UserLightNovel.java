package com.malauzet.bookshelfapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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

    private Integer currentPage;
}
