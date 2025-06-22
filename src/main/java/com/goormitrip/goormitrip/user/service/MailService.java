package com.goormitrip.goormitrip.user.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.goormitrip.goormitrip.user.exception.MailSendFailedException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender mailSender;
	private final SpringTemplateEngine thymeleaf;

	public void sendPasswordResetMail(String to, String token) {
		try {
			String link = "http://localhost:3000/reset?token=" + token;
			String html = buildHtml(link);

			MimeMessage mime = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mime, "UTF-8");
			helper.setTo(to);
			helper.setSubject("[GoormiTrip] 비밀번호 재설정 안내");
			helper.setText(html, true);

			mailSender.send(mime);
		} catch (MailException | MessagingException ex) {
			throw new MailSendFailedException();
		}
	}

	private String buildHtml(String link) {
		Context ctx = new Context();
		ctx.setVariable("resetLink", link);
		return thymeleaf.process("password-reset.html", ctx);
	}
}
