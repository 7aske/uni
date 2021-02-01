package com.example.backend.config;

import com.example.backend.data.converter.RequestPageableConverter;
import com.example.backend.data.converter.RequestParamQueryConverter;
import com.example.backend.data.converter.SpecificationConverter;
import com.example.backend.entity.Post;
import com.example.backend.entity.PostPreview;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class Config {
	private final ConverterRegistry converterRegistry;

	@PostConstruct
	public void init() {
		registerConverters();
	}

	private void registerConverters() {
		converterRegistry.addConverter(new RequestPageableConverter());
		converterRegistry.addConverter(new SpecificationConverter<>());
		converterRegistry.addConverter(new RequestParamQueryConverter());
	}

	@Bean(name = "passwordEncoder")
	public static PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean(name = "errorMessages")
	public static PropertiesFactoryBean errorMessages() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("errors.properties"));
		return bean;
	}
}