import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Book } from "../models/book.model";
import { WorkService } from "./work.service";

@Injectable({ providedIn: 'root' })
export class BookService extends WorkService<Book> {
    
    constructor(http: HttpClient) {
        super(http, '/api/books');
    }
}