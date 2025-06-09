package com.goormitrip.goormitrip.oauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goormitrip.goormitrip.oauth.domain.OAuthAccount;
import com.goormitrip.goormitrip.oauth.domain.SocialProvider;

public interface OAuthAccountRepository extends JpaRepository<OAuthAccount, Long> {

	/**
	 * Finds an OAuth account by its provider and provider user ID.
	 *
	 * @param provider the social provider (e.g., GOOGLE, NAVER, KAKAO)
	 * @param providerUserId the unique user ID provided by the social provider
	 * @return an Optional containing the found OAuthAccount, or empty if not found
	 */
	Optional<OAuthAccount> findByProviderAndProviderUserId(SocialProvider provider, String providerUserId);
}
