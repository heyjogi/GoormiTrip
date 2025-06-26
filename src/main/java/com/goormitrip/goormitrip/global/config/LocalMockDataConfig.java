package com.goormitrip.goormitrip.global.config;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.domain.UserRole;
import com.goormitrip.goormitrip.user.dto.SignupRequest;
import com.goormitrip.goormitrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class LocalMockDataConfig {

	private final UserRepository userRepo;
	private final PasswordEncoder encoder;

	@Bean
	CommandLineRunner initMockUsers() {
		return args -> {
			initAdmins();
			initDefaultUser();
		};
	}

	private void initAdmins() {
		if (userRepo.countByRole(UserRole.ADMIN) > 0)
			return;

		List<UserEntity> admins = IntStream.rangeClosed(1, 3)
			.mapToObj(i -> UserEntity.of(
				"admin" + i + "@example.com",
				encoder.encode("admin" + i + "Pass!"),
				UserRole.ADMIN))
			.toList();

		userRepo.saveAll(admins);
	}

	private void initDefaultUser() {
		String email = "example@test.com";
		String rawPassword = "qwer123!";
		String phone = "01012345678";

		if (userRepo.existsByEmail(email))
			return;

		SignupRequest request = new SignupRequest();
		request.setEmail(email);
		request.setPassword(rawPassword);
		request.setPhone(phone);

		String encodedPassword = encoder.encode(rawPassword);
		UserEntity user = UserEntity.createLocal(request, encodedPassword);
		userRepo.save(user);
	}
}
