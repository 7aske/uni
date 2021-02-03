package com.example.backend.data.converter;

import com.example.backend.data.search.RequestParamQuery;
import com.example.backend.repository.specification.GenericSpecification;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;

@NoArgsConstructor
public class SpecificationConverter<T extends Serializable> implements Converter<String, Specification<T>> {

	@Override
	public GenericSpecification<T> convert(String source) {
		RequestParamQueryConverter requestParamQueryConverter = new RequestParamQueryConverter();
		RequestParamQuery requestParamQuery = requestParamQueryConverter.convert(source);
		return new GenericSpecification<>(requestParamQuery);
	}
}
