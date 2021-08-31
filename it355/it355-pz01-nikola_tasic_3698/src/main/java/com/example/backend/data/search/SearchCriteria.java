package com.example.backend.data.search;

import lombok.Data;
import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

@Data
public class SearchCriteria implements Serializable {
	private String key;
	private String value;
	private CriteriaOp op;

	public SearchCriteria() {
	}

	public SearchCriteria(String key, CriteriaOp op, String value) {
		this.key = key;
		this.value = value;
		this.op = op;
	}

	public SearchCriteria(String key, String op, String value) {
		this.key = key;
		this.value = value;
		try {
			this.op = CriteriaOp.valueOf(op);
		} catch (Exception ignored) {
			this.op = CriteriaOp.EQ;
		}
	}

	public static SearchCriteria from(Map.Entry<String, String> kv) {
		return from(kv.getKey(), kv.getValue());
	}

	public static SearchCriteria from(String reqParamKey, String value) {
		String[] params = reqParamKey.split("\\.", reqParamKey.lastIndexOf("."));
		CriteriaOp op = CriteriaOp.EQ;
		if (params.length > 1) {
			try {
				op = CriteriaOp.valueOf(params[params.length - 1].toUpperCase());
				String key = String.join(".", Arrays.copyOfRange(params, 0, params.length - 1));
				return new SearchCriteria(key, op, value);
			} catch (Exception ignored) {
				// ignored
			}
		}
		return new SearchCriteria(reqParamKey, op, value);
	}
}
