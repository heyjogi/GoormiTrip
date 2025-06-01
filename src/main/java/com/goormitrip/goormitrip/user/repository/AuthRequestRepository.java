package com.goormitrip.goormitrip.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goormitrip.goormitrip.user.dto.AuthRequest;

public interface AuthRequestRepository extends JpaRepository<AuthRequest, String> {
}
