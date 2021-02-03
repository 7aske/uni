package com.example.cms.config;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Config {
	private static final String bundlePath = "i18n/strings";
	private static final String propertiesPath = "config.properties";

	public static final Locale DEFAULT_LOCALE = new Locale("en");

	private static Properties properties = null;

	public static Properties getProperties() {
		if (properties == null) {
			try {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				properties = new Properties();
				properties.load(classLoader.getResourceAsStream(propertiesPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

	public static ResourceBundle getBundle() {
		return ResourceBundle.getBundle(bundlePath, DEFAULT_LOCALE);
	}

	public static ResourceBundle getBundle(String _locale) {
		Locale locale = new Locale(_locale);

		return ResourceBundle.getBundle(bundlePath, locale);
	}
}
