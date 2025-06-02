package com.goormitrip.goormitrip.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;

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
}


