import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { LightNovel } from "../models/light-novel.model";
import { WorkService } from "./work.service";

@Injectable({ providedIn: 'root' })
export class LightNovelService extends WorkService<LightNovel> {
    
    constructor(http: HttpClient) {
        super(http, '/api/light-novels');
    }
}