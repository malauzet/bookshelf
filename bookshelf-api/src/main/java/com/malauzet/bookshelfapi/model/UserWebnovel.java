package com.malauzet.bookshelfapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Tracking state for a {@link User}'s progress through a {@link Webnovel}. Adds no field beyond
 * {@link UserWork} — chapter progress is the only unit of progress a webnovel needs.
 */
@Entity
@DiscriminatorValue("WEBNOVEL")
@Getter
@Setter
@NoArgsConstructor
public class UserWebnovel extends UserWork {
}
