package com.malauzet.bookshelfapi.exception;

import java.util.Map;

public record ErrorResponse(String message, Map<String, String> fieldError) {
}
