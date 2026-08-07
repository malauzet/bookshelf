import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserAudiobook } from "../models/audiobook.model";
import { UserTrackingService } from "./user-tracking.service";

@Injectable({ providedIn: 'root' })
export class UserAudiobookService extends UserTrackingService<UserAudiobook> {

    constructor(http: HttpClient) {
        super(http, 'audiobooks');
    }
}