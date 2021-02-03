package com.example.cms.security;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Security {
	public static String hash(final String originalString) {
		return Hashing.sha256().hashString(originalString, StandardCharsets.UTF_8).toString();
	}
}
