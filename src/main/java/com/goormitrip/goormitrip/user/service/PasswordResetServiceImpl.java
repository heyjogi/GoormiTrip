package com.goormitrip.goormitrip.user.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.user.domain.PasswordResetToken;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.exception.InvalidResetTokenException;
import com.goormitrip.goormitrip.user.repository.PasswordResetTokenRepository;
import com.goormitrip.goormitrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

	private final UserRepository userRepo;
	private final PasswordResetTokenRepository tokenRepo;
	private final PasswordEncoder encoder;
	private final MailService mailService;

	@Transactional
	public void requestReset(String email) {
		userRepo.findByEmail(email).ifPresentOrElse(user -> {
			String token = UUID.randomUUID().toString();
			tokenRepo.save(PasswordResetToken.create(user, token));
			mailService.sendPasswordResetMail(email, token);
		}, () -> log.info("Password reset requested for non-existent email {}", email));
	}

	@Transactional
	public void resetPassword(String token, String newPwd) {
		PasswordResetToken prt = tokenRepo.findByToken(token)
				.orElseThrow(InvalidResetTokenException::new);

		if (prt.isExpired() || prt.isUsed()) {
			throw new InvalidResetTokenException();
		}

		UserEntity user = prt.getUser();
		user.changePassword(encoder.encode(newPwd));
		prt.markUsed();
	}
}
