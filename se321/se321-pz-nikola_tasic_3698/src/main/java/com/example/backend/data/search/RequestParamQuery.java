package com.example.backend.data.search;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RequestParamQuery implements Serializable {
	private List<SearchCriteria> criteria;
	private QueryOp op;
}
