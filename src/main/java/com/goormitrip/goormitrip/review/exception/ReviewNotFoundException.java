package com.goormitrip.goormitrip.review.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;
import com.goormitrip.goormitrip.global.util.exception.CommonError;

public class ReviewNotFoundException extends BusinessException {
	public ReviewNotFoundException() {
		super(CommonError.RESOURCE_NOT_FOUND);
	}
}
