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
import com.goormitrip.goormitrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class LocalMockDataConfig {

	private final UserRepository userRepo;
	private final PasswordEncoder encoder;

	@Bean
	CommandLineRunner initAdmins() {
		return args -> {
			if (userRepo.countByRole(UserRole.ADMIN) > 0) return;

			List<UserEntity> admins = IntStream.rangeClosed(1, 3)
				.mapToObj(i -> UserEntity.of(
					"admin" + i + "@example.com",
					encoder.encode("admin" + i + "Pass!"),
					UserRole.ADMIN))
				.toList();

			userRepo.saveAll(admins);
		};
	}
}
