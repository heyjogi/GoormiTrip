package com.goormitrip.goormitrip.global.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JsonContentTypeFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		boolean isJsonMethod = "POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod());

		if (isJsonMethod &&
			!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType())) {

			response.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
			response.setCharacterEncoding("UTF-8");
			response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
			response.getWriter().write("""
                {
                  "success": false,
                  "response": null,
                  "error": {
                    "message": "요청 형식이 잘못되었습니다. JSON 형식인지 확인해주세요.",
                    "status": 415
                  }                }
                """);
			return;
		}

		filterChain.doFilter(request, response);
	}
}
