package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** CRUD plus username lookups for {@link User} registration/login. */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    /** Used to reject a duplicate registration before hitting the DB's unique constraint. */
    boolean existsByUsername(String username);
}
