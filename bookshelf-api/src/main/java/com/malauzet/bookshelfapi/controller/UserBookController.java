package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.BookNotFoundException;
import com.malauzet.bookshelfapi.exception.DuplicateTrackingException;
import com.malauzet.bookshelfapi.exception.UserNotFoundException;
import com.malauzet.bookshelfapi.model.Book;
import com.malauzet.bookshelfapi.model.User;
import com.malauzet.bookshelfapi.model.UserBook;
import com.malauzet.bookshelfapi.repository.BookRepository;
import com.malauzet.bookshelfapi.repository.UserBookRepository;
import com.malauzet.bookshelfapi.repository.UserRepository;
import com.malauzet.bookshelfapi.repository.UserWorkRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Owns only the {@code Book}-specific "start tracking" endpoint — everything else on an
 * already-tracked {@link UserBook} (get/patch/delete) is handled generically by
 * {@link UserWorkController} (see its class Javadoc for why).
 */
@RestController
@RequestMapping("/api/users/{userId}")
@RequiredArgsConstructor
public class UserBookController {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserWorkRepository userWorkRepository;
    private final UserBookRepository userBookRepository;

    /**
     * @throws UserNotFoundException if {@code userId} doesn't resolve
     * @throws BookNotFoundException if {@code bookId} doesn't resolve
     * @throws DuplicateTrackingException if {@code userId} is already tracking this book
     */
    @PostMapping("/books/{bookId}")
    public ResponseEntity<UserBook> trackBook(@PathVariable Long userId, @PathVariable Long bookId,
                                              @RequestBody @Valid UserBook userBook) {

        User user = getUser(userId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));

        if (userWorkRepository.existsByUserAndWork(user, book)) {
            throw new DuplicateTrackingException("User " + userId + " is already tracking work " + bookId);
        }

        userBook.setUser(user);
        userBook.setWork(book);

        UserBook saved = userBookRepository.save(userBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }
}