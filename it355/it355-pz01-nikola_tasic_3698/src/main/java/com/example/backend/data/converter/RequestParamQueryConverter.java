package com.example.backend.data.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.backend.data.search.RequestParamQuery;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.util.Base64;

@NoArgsConstructor
public class RequestParamQueryConverter implements Converter<String, RequestParamQuery> {

	@Override
	public RequestParamQuery convert(String str) {
		try {
			byte[] decoded = Base64.getDecoder().decode(str);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(decoded, RequestParamQuery.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
