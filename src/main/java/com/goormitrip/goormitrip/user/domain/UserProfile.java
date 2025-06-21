package com.goormitrip.goormitrip.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "user_profiles")
@Getter
public class UserProfile {

	@Id
	private Long id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private UserEntity user;

	private String nickname;
	private String profileImageUrl;
	private String phone;
	private String address;

	@Builder
	public static UserProfile of(UserEntity user, String nickname, String imageUrl, String phone, String address) {
		UserProfile profile = new UserProfile();
		profile.user = user;
		profile.nickname = nickname;
		profile.profileImageUrl = imageUrl;
		profile.phone = phone;
		profile.address = address;
		return profile;
	}

	public void updateProfile(String nickname, String imageUrl, String phone, String address) {
		this.nickname = nickname;
		this.profileImageUrl = imageUrl;
		this.phone = phone;
		this.address = address;
	}
}
