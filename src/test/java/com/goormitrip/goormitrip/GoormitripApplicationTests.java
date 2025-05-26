package com.goormitrip.goormitrip;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class GoormitripApplicationTests {

	@Configuration
	static class TestConfig {
		@Bean
		public PasswordEncoder passwordEncoder() {
			return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
		}
	}

	@Test
	void contextLoads() {
	}
}
