import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserTrackingService } from "./user-tracking.service";
import { UserManga } from "../models/manga.model";

@Injectable({ providedIn: 'root' })
export class UserMangaService extends UserTrackingService<UserManga> {

    constructor(http: HttpClient) {
        super(http, 'mangas');
    }
}