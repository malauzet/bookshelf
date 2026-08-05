package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.UserWebnovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWebnovelRepository extends JpaRepository<UserWebnovel, Long> {
}
