package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.ReadingStatus;
import com.malauzet.bookshelfapi.model.User;
import com.malauzet.bookshelfapi.model.UserWork;
import com.malauzet.bookshelfapi.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserWorkRepository extends JpaRepository<UserWork, Long> {

    List<UserWork> findByUserAndStatus(User user, ReadingStatus status);

    boolean existsByUserAndWork(User user, Work work);
}
