package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class RequiredTermsUncheckedException extends BusinessException {
	public RequiredTermsUncheckedException() {
		super(UserError.REQUIRED_TERMS_UNCHECKED);
	}
}