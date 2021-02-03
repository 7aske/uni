package com.example.backend.data;

import lombok.Data;

@Data
public class ValueContainer<T> {
	private T value;

	public static <T> ValueContainer<T> of(T value) {
		ValueContainer<T> valueContainer = new ValueContainer<>();
		valueContainer.value = value;
		return valueContainer;
	}
}
