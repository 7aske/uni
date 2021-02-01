package com.example.backend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapUtils {
	/**
	 * Converts(zips) two arrays into a map with first array argument representing map keys
	 * and second argument representing map values.
	 *
	 * @param keysArray   Map keys array.
	 * @param valuesArray Map values array.
	 * @return Converted map.
	 */
	public static <K, V> Map<K, V> zip(K[] keysArray, V[] valuesArray) {
		if (keysArray.length != valuesArray.length) {
			throw new IllegalArgumentException();
		}
		return IntStream.range(0, keysArray.length)
				.boxed()
				.collect(Collectors.toMap(i -> keysArray[i], i -> valuesArray[i]));
	}
}
