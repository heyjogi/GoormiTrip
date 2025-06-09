package com.goormitrip.goormitrip.global.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.domain.UserRole;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {
	private final UserEntity user;
	private final Long id;
	private final String email;
	private final String password;
	private final UserRole role;

	public CustomUserDetails(UserEntity user) {
		this.user = user;
		this.id = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}

	public UserEntity toUserEntityForTokenStorage() {
		UserEntity user = new UserEntity();
		user.setId(this.id);
		return user;
	}
}
