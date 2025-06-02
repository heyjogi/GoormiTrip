package com.goormitrip.goormitrip.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goormitrip.goormitrip.user.domain.PhoneVerification;

public interface AuthRequestRepository extends JpaRepository<PhoneVerification, String> {
}
