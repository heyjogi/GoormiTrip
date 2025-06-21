package com.goormitrip.goormitrip.oauth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.oauth.domain.OAuthAccount;
import com.goormitrip.goormitrip.oauth.dto.OAuthAttributes;
import com.goormitrip.goormitrip.oauth.repository.OAuthAccountRepository;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SocialUserFacade {

	private final OAuthAccountRepository oauthRepo;
	private final UserRepository userRepo;

	@Transactional
	public UserEntity findOrCreate(OAuthAttributes attrs) {
		return oauthRepo.findByProviderAndProviderUserId(
				attrs.provider(), attrs.providerUserId())
			.map(OAuthAccount::getUser)
			.orElseGet(() -> signUpSocial(attrs));
	}

	private UserEntity signUpSocial(OAuthAttributes a) {
		UserEntity user = UserEntity.createSocial(a);
		OAuthAccount account = OAuthAccount.builder()
			.provider(a.provider())
			.providerUserId(a.providerUserId())
			.email(a.email())
			.user(user)
			.build();

		userRepo.save(user);
		oauthRepo.save(account);

		return user;
	}
}