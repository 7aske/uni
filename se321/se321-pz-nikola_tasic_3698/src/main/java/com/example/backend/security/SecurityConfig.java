package com.example.backend.security;

import com.example.backend.repository.UserRepository;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final JwtProvider jwtProvider;
	private final UserRepository userRepository;
	private final Environment env;

	@Resource(name = "errorMessages")
	private Properties errorMessages;


	@Value("${security.disabled:false}")
	private String securityDisabled;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Boolean.parseBoolean(securityDisabled) || Arrays.asList(env.getActiveProfiles()).contains("dev")) {
			http.httpBasic().disable()
					.cors().and()
					.csrf().disable()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/auth/validate").authenticated().and()
					.authorizeRequests()
					.antMatchers("/**").permitAll()
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().and()
					.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtProvider, errorMessages))
					.addFilterBefore(new JwtAuthorizationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
		} else {
			http.httpBasic().disable()
					.cors().and()
					.csrf().disable()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
					.authorizeRequests()
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					.antMatchers(HttpMethod.POST, "/login").permitAll()
					.antMatchers(HttpMethod.GET, "/posts").permitAll()
					.antMatchers(HttpMethod.GET, "/posts/*").permitAll()
					.anyRequest().authenticated()
					.and()
					.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtProvider, errorMessages))
					.addFilterBefore(new JwtAuthorizationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
		}
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager() {
		return new AuthenticationManagerImpl(userRepository, env);
	}


	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(ImmutableList.of("*"));
		configuration.setAllowedMethods(ImmutableList.of(
				HttpMethod.GET.name(),
				HttpMethod.DELETE.name(),
				HttpMethod.PUT.name(),
				HttpMethod.POST.name(),
				HttpMethod.OPTIONS.name()));
		configuration.setAllowedHeaders(ImmutableList.of(
				HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
				HttpHeaders.AUTHORIZATION,
				HttpHeaders.CONTENT_TYPE));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}