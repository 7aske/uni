package com.example.backend.security;

import com.example.backend.data.ErrorInfo;
import com.example.backend.data.LoginResponse;
import com.example.backend.entity.User;
import com.example.backend.util.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.Properties;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final Properties errorMessages;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		User user = ObjectMapperUtils.readValue(request, User.class);

		if (user == null)
			throw new UsernameNotFoundException(errorMessages.getProperty("auth.login.invalid-credentials"));

		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
		String token = jwtProvider.createToken(authResult.getName(), authResult.getAuthorities());
		LoginResponse loginResponse = new LoginResponse(token);
		ObjectMapperUtils.writeValue(response, loginResponse, HttpStatus.OK.value());
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.UNAUTHORIZED, request, ex.getMessage());
		ObjectMapperUtils.writeValue(response, errorInfo, HttpStatus.UNAUTHORIZED.value());
	}

}