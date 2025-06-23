package com.goormitrip.goormitrip.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.domain.UserRole;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    @EntityGraph(attributePaths = "profile")
    Optional<UserEntity> findFetchProfileById(Long id);

    List<UserEntity> findAllByRole(UserRole role);

    long countByRole(UserRole role);
}
