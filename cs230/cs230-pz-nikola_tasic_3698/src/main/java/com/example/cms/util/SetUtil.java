package com.example.cms.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SetUtil {
	public static <E> Collection<E> inter(final Collection<E> listA, Collection<E> listB) {
		Set<E> setA = new HashSet<>(listA);
		Set<E> setB = new HashSet<>(listB);

		// Set<E> union = new HashSet<>(setA);
		// union.addAll(setB);

		Set<E> intersection = new HashSet<>(setA);
		intersection.retainAll(setB);

		return intersection;

		// Set<E> symmetricDifference = new HashSet<>(union);
		// symmetricDifference.removeAll(intersection);
		//
		// return symmetricDifference;
	}
}
