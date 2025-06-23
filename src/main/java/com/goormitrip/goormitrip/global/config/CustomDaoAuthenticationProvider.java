package com.goormitrip.goormitrip.global.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.user.exception.SocialLoginUserCannotLoginException;

@Component
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

	public CustomDaoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder encoder) {
		setUserDetailsService(userDetailsService);
		setPasswordEncoder(encoder);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken auth)
		throws AuthenticationException {

		CustomUserDetails custom = (CustomUserDetails) userDetails;
		if (custom.isSocialUser()) {
			throw new SocialLoginUserCannotLoginException();
		}
		super.additionalAuthenticationChecks(userDetails, auth);
	}
}