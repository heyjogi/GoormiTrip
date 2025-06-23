package com.goormitrip.goormitrip.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.goormitrip.goormitrip.global.security.JwtAuthFilter;
import com.goormitrip.goormitrip.oauth.security.JwtIssuerSuccessHandler;
import com.goormitrip.goormitrip.oauth.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final UserDetailsService userDetailsService;
	private final JwtAuthFilter jwtAuthFilter;

	private final CustomOAuth2UserService oAuth2UserSvc;
	private final JwtIssuerSuccessHandler successHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers(
						new AntPathRequestMatcher("/"),
						new AntPathRequestMatcher("/health"),
						new AntPathRequestMatcher("/users/**"),
						new AntPathRequestMatcher("/auth/**"),
						new AntPathRequestMatcher("/error"),
						new AntPathRequestMatcher("/api/products/**"),
						new AntPathRequestMatcher("/verification/phone/**"),
						new AntPathRequestMatcher("/kakao/webhook"),
						new AntPathRequestMatcher("/oauth2/**")).permitAll().anyRequest().authenticated())
				.authenticationProvider(authenticationProvider())
				.oauth2Login(o -> o
						.userInfoEndpoint(u -> u.userService(oAuth2UserSvc))
						.successHandler(successHandler))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
