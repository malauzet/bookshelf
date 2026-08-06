package com.malauzet.bookshelfapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A registered application user. Table is explicitly named {@code app_user} rather than
 * {@code user}, since {@code USER} is a reserved word in MariaDB/MySQL (and H2). No Spring
 * Security wiring yet — {@link #password} is stored in plaintext for now, by design, until that
 * milestone is picked up.
 */
@Entity
@Table(name = "app_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /** Technical primary key, server-generated — never accepted from client input. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    /** Primary login credential; unique and required. */
    @NotBlank(message = "Username is required.")
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    /** Plaintext for now (see class Javadoc); never serialized back to the client. */
    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /** Optional — not required to use the app. Nullable {@code UNIQUE} allows multiple
     *  {@code NULL} rows while still rejecting a duplicate of any actual value. */
    @Column(unique = true)
    private String email;
}
