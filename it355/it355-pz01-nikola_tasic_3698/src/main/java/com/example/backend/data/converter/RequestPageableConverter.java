package com.example.backend.data.converter;

import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor
public class RequestPageableConverter implements Converter<String, Pageable> {
	public static final int DEFAULT_PAGE_SIZE = 10;

	@Override
	public Pageable convert(String source) {
		String[] params = source.split(",");
		int page = 0;
		int count = DEFAULT_PAGE_SIZE;
		try {
			if (params.length == 2) {
				count = Integer.parseInt(params[1]);
			}
			page = Integer.parseInt(params[0]);
		} catch (NumberFormatException ignored) {
			// ignored
		}
		return PageRequest.of(page, count);
	}
}
