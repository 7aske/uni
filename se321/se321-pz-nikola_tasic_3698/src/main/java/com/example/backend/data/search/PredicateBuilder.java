package com.example.backend.data.search;


import lombok.val;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PredicateBuilder<T> {
	private final Root<T> root;
	private final CriteriaQuery<?> query;
	private final CriteriaBuilder builder;
	private final List<SearchCriteria> criteria;
	private QueryOp op = QueryOp.OR;

	public PredicateBuilder(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		this.root = root;
		this.query = query;
		this.builder = builder;
		this.criteria = new ArrayList<>();
	}

	public PredicateBuilder<T> with(SearchCriteria... searchCriteria) {
		this.criteria.addAll(Arrays.asList(searchCriteria));
		return this;
	}

	public PredicateBuilder<T> with(RequestParamQuery query) {
		this.op = query.getOp();
		this.criteria.addAll(query.getCriteria());
		return this;
	}

	public PredicateBuilder<T> with(String key, CriteriaOp operation, String value) {
		SearchCriteria searchCriteria = new SearchCriteria(key, operation, value);
		this.criteria.add(searchCriteria);
		return this;
	}
	
	public Predicate build() {
		List<Predicate> predicates = new ArrayList<>();

		for (SearchCriteria cr : this.criteria) {
			String[] params = cr.getKey().split("\\.");
			String base = params.length > 1 ? params[1] : params[0];
			From<T, ?> join = params.length > 1 ? this.root.join(params[0]): this.root;

			switch (cr.getOp()){
				case GT:
					predicates.add(builder.greaterThan(join.get(base).as(String.class), cr.getValue()));
					break;
				case LT:
					predicates.add(builder.lessThan(join.get(base).as(String.class), cr.getValue()));
					break;
				case GTE:
					predicates.add(builder.greaterThanOrEqualTo(join.get(base).as(String.class), cr.getValue()));
					break;
				case LTE:
					predicates.add(builder.lessThanOrEqualTo(join.get(base).as(String.class), cr.getValue()));
					break;
				case NE:
					predicates.add(builder.notEqual(join.get(base).as(String.class), cr.getValue()));
					break;
				case EQ:
					predicates.add(builder.equal(join.get(base).as(String.class), cr.getValue()));
					break;
				case MATCH:
					predicates.add(builder.like(builder.lower(join.get(base).as(String.class)), "%" + cr.getValue().toLowerCase() + "%"));
					break;
				case START:
					predicates.add(builder.like(builder.lower(join.get(base).as(String.class)), "%" + cr.getValue().toLowerCase()));
					break;
				case END:
					predicates.add(builder.like(builder.lower(join.get(base).as(String.class)), cr.getValue().toLowerCase() + "%"));
					break;
				case IN:
					predicates.add(builder.in(join.get(base).as(String.class)).value(cr.getValue()));
					break;
				case NOT_IN:
					predicates.add(builder.not(join.get(base)).in(cr.getValue()));
					break;
			}
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);

		if (this.op == QueryOp.AND) {
			return builder.and(predicatesArray);
		}
		return builder.or(predicatesArray);

	}
}
