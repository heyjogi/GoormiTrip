package com.goormitrip.goormitrip.repository;

import com.goormitrip.goormitrip.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
