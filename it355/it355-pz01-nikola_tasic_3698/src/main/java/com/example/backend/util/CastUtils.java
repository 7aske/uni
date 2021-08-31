package com.example.backend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CastUtils {
	public static <A extends B, B> List<A> cast(List<B> list, Class<A> clazz) {
		return list.stream()
				.map(clazz::cast)
				.collect(Collectors.toList());
	}
}
