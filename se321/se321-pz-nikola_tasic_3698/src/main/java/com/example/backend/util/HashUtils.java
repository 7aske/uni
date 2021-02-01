package com.example.backend.util;

import com.google.common.hash.Hashing;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HashUtils {
	@SuppressWarnings("UnstableApiUsage")
	public static String getSha256(String str) {
		return Hashing.sha256().hashString(str, StandardCharsets.UTF_8).toString();
	}

	@SuppressWarnings("UnstableApiUsage")
	public static String getSha512(String str) {
		return Hashing.sha512().hashString(str, StandardCharsets.UTF_8).toString();
	}
}
