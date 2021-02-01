package com.example.backend.data.pagination;

import lombok.Data;

@Data
public class PaginationParamQuery {
	private int count = 10;
	private int page;
}
