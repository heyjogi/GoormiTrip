package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class KeywordRequiredException extends BusinessException {
    public KeywordRequiredException() {
        super(ProductError.KEYWORD_MISSING);
    }
}