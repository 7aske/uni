package com.example.backend.repository.specification;

import com.example.backend.data.search.PredicateBuilder;
import com.example.backend.data.search.RequestParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

@RequiredArgsConstructor
public class GenericSpecification<T extends Serializable> implements Specification<T>, Serializable {
	private final transient RequestParamQuery paramQuery;

	protected RequestParamQuery adaptKeys(RequestParamQuery query) {
		return query;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		return new PredicateBuilder<>(root, criteriaQuery, criteriaBuilder)
				.with(adaptKeys(this.paramQuery))
				.build();
	}
}