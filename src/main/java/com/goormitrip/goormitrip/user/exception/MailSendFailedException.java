package com.goormitrip.goormitrip.user.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class MailSendFailedException extends BusinessException {
	public MailSendFailedException() {
		super(UserError.MAIL_SEND_FAILED);
	}
}
