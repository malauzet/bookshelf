import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Manga } from "../models/manga.model";
import { WorkService } from "./work.service";

@Injectable({ providedIn: 'root' })
export class MangaService extends WorkService<Manga> {
    
    constructor(http: HttpClient) {
        super(http, '/api/mangas');
    }
}