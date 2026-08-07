import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserLightNovel } from "../models/light-novel.model";
import { UserTrackingService } from "./user-tracking.service";

@Injectable({ providedIn: 'root' })
export class UserLightNovelService extends UserTrackingService<UserLightNovel> {

    constructor(http: HttpClient) {
        super(http, 'light-novels');
    }
}