package com.goormitrip.goormitrip.global.util.response;

record ApiError(
	String message,
	int status
) {
}