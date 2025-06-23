package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidResetTokenException extends BusinessException {
	public InvalidResetTokenException() {super(UserError.INVALID_RESET_TOKEN);}
}
