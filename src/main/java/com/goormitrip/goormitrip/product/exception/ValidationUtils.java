package com.goormitrip.goormitrip.product.exception;

public class ValidationUtils {

    public static void validateKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new KeywordRequiredException();
        }
    }
}
