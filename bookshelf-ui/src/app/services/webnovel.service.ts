import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Webnovel } from "../models/webnovel.model";
import { WorkService } from "./work.service";

@Injectable({ providedIn: 'root' })
export class WebnovelService extends WorkService<Webnovel> {
    
    constructor(http: HttpClient) {
        super(http, '/api/webnovels');
    }
}