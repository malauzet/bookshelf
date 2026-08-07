import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserTrackingService } from "./user-tracking.service";
import { UserBook } from "../models/book.model";

@Injectable({ providedIn: 'root' })
export class UserBookService extends UserTrackingService<UserBook> {

    constructor(http: HttpClient) {
        super(http, 'books');
    }
}