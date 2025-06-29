package com.goormitrip.goormitrip.review.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;
import com.goormitrip.goormitrip.global.util.exception.CommonError;

public class ReviewAccessDeniedException extends BusinessException {
	public  ReviewAccessDeniedException() {
		super(CommonError.RESOURCE_NOT_FOUND);
	}
}
