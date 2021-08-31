package com.example.backend.entity.domain;


/**
 * Contact type. Email, phone, address...
 */
public enum ContactType {
	ADDRESS("address"),
	PHONE("phone"),
	EMAIL("email"),
	WEBSITE("website");

	private final String name;

	ContactType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}