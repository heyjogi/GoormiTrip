package com.goormitrip.goormitrip.global.security;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
	public JwtAuthenticationException(String msg) { super(msg); }
	public JwtAuthenticationException(String msg, Throwable t) { super(msg, t); }
}