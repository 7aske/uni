package com.example.backend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IOUtils {

	public static void deleteFileIfExistsNoExcept(String path) {
		deleteFileIfExistsNoExcept(Paths.get(path));
	}

	public static void deleteFileIfExistsNoExcept(Path path) {
		try {
			Files.deleteIfExists(path);
		} catch (IOException ignored) {
			// ignored
		}
	}

	public static void createDirectoriesNoExcept(String path) {
		createDirectoriesNoExcept(Paths.get(path));
	}

	public static void createDirectoriesNoExcept(Path path) {
		try {
			Files.createDirectories(path);
		} catch (IOException ignored) {
			// ignored
		}

	}
}
