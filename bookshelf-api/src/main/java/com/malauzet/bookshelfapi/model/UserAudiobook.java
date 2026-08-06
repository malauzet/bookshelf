package com.malauzet.bookshelfapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("AUDIOBOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAudiobook extends UserWork {

    @Positive(message = "Current minutes must be greater than 0")
    private Integer currentMinutes;
}
