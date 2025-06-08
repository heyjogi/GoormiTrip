package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class PhoneVerificationFailedException extends BusinessException {
	public PhoneVerificationFailedException() {
		super(UserError.PHONE_VERIFICATION_FAILED);
	}
}