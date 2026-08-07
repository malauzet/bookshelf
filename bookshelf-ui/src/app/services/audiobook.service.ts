import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Audiobook } from "../models/audiobook.model";
import { WorkService } from "./work.service";

@Injectable({ providedIn: 'root' })
export class AudiobookService extends WorkService<Audiobook> {
    
    constructor(http: HttpClient) {
        super(http, '/api/audiobooks');
    }
}