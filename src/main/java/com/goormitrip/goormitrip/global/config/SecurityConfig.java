package com.goormitrip.goormitrip.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
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

import com.goormitrip.goormitrip.global.security.CustomAccessDeniedHandler;
import com.goormitrip.goormitrip.global.security.JwtAuthEntryPoint;
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

	private final JwtAuthEntryPoint authEntryPoint;
	private final CustomAccessDeniedHandler deniedHandler;

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
	public AuthenticationProvider authenticationProvider() {
		return new CustomDaoAuthenticationProvider(userDetailsService, passwordEncoder());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/", "/health", "/error").permitAll()
						.requestMatchers("/auth/**", "/oauth2/**").permitAll()
						.requestMatchers("/verification/phone/**", "/kakao/webhook").permitAll()
						// 25.06.21 회원가입과 로그인 로직은 허용하도록 했습니다
						.requestMatchers(HttpMethod.POST, "/users/**").permitAll()
						.requestMatchers("/api/products/**").permitAll()
						.anyRequest().authenticated())
				.formLogin(AbstractHttpConfigurer::disable)
				.httpBasic(AbstractHttpConfigurer::disable)
				.exceptionHandling(ex -> ex
						.authenticationEntryPoint(authEntryPoint)
						.accessDeniedHandler(deniedHandler))
				.authenticationProvider(authenticationProvider())
				.oauth2Login(o -> o
						.userInfoEndpoint(u -> u.userService(oAuth2UserSvc))
						.successHandler(successHandler))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
