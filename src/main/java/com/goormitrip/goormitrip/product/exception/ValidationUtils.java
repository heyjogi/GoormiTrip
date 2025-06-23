package com.goormitrip.goormitrip.product.exception;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidationUtils {

    private static final Set<String> ALLOWED_REGIONS = new HashSet<>(Arrays.asList("jeju", "busan", "seoul"));
    private static final Set<String> ALLOWED_THEMES = new HashSet<>(Arrays.asList("healing", "food", "activity"));
    private static final Set<String> ALLOWED_SORT_KEYS = Set.of("latest", "price");

    public static void validateRegion(String region) {
        if (region != null && !ALLOWED_REGIONS.contains(region)) {
            throw new InvalidRequestParameterException("유효하지 않은 필터 값입니다.");
        }
    }

    public static void validateTheme(String theme) {
    if (theme != null && !ALLOWED_THEMES.contains(theme)) {
            throw new InvalidRequestParameterException("유효하지 않은 필터 값입니다.");
        }
    }

    public static void validateSort(String sort) {
		if (sort != null && !ALLOWED_SORT_KEYS.contains(sort)) {
			throw new InvalidRequestParameterException("유효하지 않은 필터 값입니다.");
		}
	}
}
