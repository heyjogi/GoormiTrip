package com.goormitrip.goormitrip.user.domain;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String phone;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role = UserRole.USER;

	@Builder
	public static UserEntity of(String email, String password, String phone, UserRole role) {
		UserEntity user = new UserEntity();
		user.email = email;
		user.password = password;
		user.phone = phone;
		user.role = role;
		return user;
	}

	public boolean isSocialUser() {
		return UserRole.SOCIAL_USER == role;
	}
}


