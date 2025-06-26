package com.goormitrip.goormitrip.chat.domain;

public enum InquiryType {
	PAYMENT, PRODUCT, REFUND, RESERVATION, ETC;

	public static InquiryType from(String raw) {
		if (raw == null || raw.isBlank()) return ETC;

		try {
			return InquiryType.valueOf(raw.trim().toUpperCase());
		} catch (IllegalArgumentException e) {
			return ETC;
		}
	}
}