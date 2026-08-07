import { Component, OnInit, signal } from '@angular/core';
import { Book } from '../models/book.model';
import { BookService } from '../services/book.service';

@Component({
    selector: 'app-book-list',
    imports: [],
    templateUrl: './book-list.html',
    styleUrl: './book-list.css',
})
export class BookList implements OnInit {

    books = signal<Book[]>([]);
    loading = signal(true);
    error = signal<string | null>(null);

    constructor(private bookService: BookService) {}

    ngOnInit(): void {

        this.bookService.getAll().subscribe({
            next: (books) => {
                this.books.set(books);
                this.loading.set(false);
            },
            error: () => {
                this.error.set('Failed to load books.');
                this.loading.set(false);
            }
        });
    }
}
