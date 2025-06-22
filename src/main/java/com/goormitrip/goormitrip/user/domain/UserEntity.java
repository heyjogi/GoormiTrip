package com.goormitrip.goormitrip.user.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;
import com.goormitrip.goormitrip.oauth.domain.OAuthAccount;
import com.goormitrip.goormitrip.oauth.dto.OAuthAttributes;
import com.goormitrip.goormitrip.user.dto.SignupRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role = UserRole.USER;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private UserProfile profile;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OAuthAccount> oauthAccounts = new ArrayList<>();

	@Builder
	public static UserEntity of(String email, String password, UserRole role) {
		UserEntity user = new UserEntity();
		user.email = email;
		user.password = password;
		user.role = role;
		return user;
	}

	public static UserEntity createLocal(SignupRequest req, String encodedPwd) {
		UserEntity user = UserEntity.builder()
			.email(req.getEmail())
			.password(encodedPwd)
			.role(UserRole.USER)
			.build();

		UserProfile profile = UserProfile.builder()
			.user(user)
			.phone(req.getPhone())
			.build();
		user.setProfile(profile);

		return user;
	}

	public static UserEntity createSocial(OAuthAttributes attrs) {
		return UserEntity.builder()
			.email(attrs.email())
			.password(UUID.randomUUID().toString())
			.role(UserRole.SOCIAL_USER)
			.build()
			.withProfile(attrs.nickname());
	}

	private UserEntity withProfile(String nickname) {
		this.profile = UserProfile.of(this, nickname, null, null, null);
		return this;
	}

	public boolean isSocialUser() {
		return UserRole.SOCIAL_USER == role;
	}

	public void changePassword(String encodedNewPwd) {
		this.password = encodedNewPwd;
	}
}


