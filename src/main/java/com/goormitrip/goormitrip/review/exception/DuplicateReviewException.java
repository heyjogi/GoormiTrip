package com.goormitrip.goormitrip.review.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class DuplicateReviewException extends BusinessException {
	public DuplicateReviewException() {
		super(ReviewError.DUPLICATE_REVIEW);
	}
}
