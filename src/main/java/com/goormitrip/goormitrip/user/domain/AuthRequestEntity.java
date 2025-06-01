package com.goormitrip.goormitrip.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AuthRequestEntity {

	@Id
	private String phone;

	private boolean verified;

	public AuthRequestEntity(String phone, boolean verified) {
		this.phone = phone;
		this.verified = verified;
	}

}
