package com.example.backend.security;

import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.backend.security.SecurityConstants.*;

@Component
public class JwtProvider {
	private final UserService userService;
	@Value("${jwt.secret-key:secret}")
	private String secretKey;
	@Value("${jwt.expire-length:7200000}")
	private long validityInMilliseconds;

	public JwtProvider(UserService userService) {
		this.userService = userService;
	}

	public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		Claims claims = Jwts.claims();
		claims.put(CLAIM_ROLES_KEY, getAuthoritiesAsStringList(authorities));
		claims.put("user", username);

		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(validity)
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}

	private static List<String> getAuthoritiesAsStringList(Collection<? extends GrantedAuthority> authorities){
		return authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
	}

	public Authentication getAuthentication(String token) {
		User user = this.userService.findByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getRoles());
	}

	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	private Claims getClaims(String token){
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(HEADER_STRING);

		if (bearerToken == null || !bearerToken.startsWith(TOKEN_PREFIX)) {
			return null;
		}

		return bearerToken.substring(TOKEN_PREFIX.length());
	}

	public boolean validateToken(String token) {
		try {
			Claims claims = getClaims(token);
			return !claims.getExpiration().before(new Date());
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
