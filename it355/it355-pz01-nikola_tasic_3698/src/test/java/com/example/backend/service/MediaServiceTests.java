package com.example.backend.service;


import com.example.backend.entity.Media;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.example.backend.util.TestUtils.createMockImage;
import static com.example.backend.util.TestUtils.getMultipartFile;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class MediaServiceTests {
	@Autowired
	MediaService mediaService;

	static File mockFile;

	@BeforeEach
	void classSetup() throws IOException {
		mockFile = new File("test.png");
		createMockImage(mockFile);
	}

	@AfterEach
	void teardown() throws IOException {
		mediaService.findAll()
				.forEach(media -> mediaService.deleteById(media.getId()));
		Files.deleteIfExists(Paths.get(mockFile.getPath()));
	}

	@AfterAll
	static void classTeardown() throws IOException {
		Files.deleteIfExists(Paths.get("static/media"));
		Files.deleteIfExists(Paths.get("static"));
	}

	@Test
	void test_uploadFile() throws IOException {
		MultipartFile file = getMultipartFile(mockFile, "image/png");
		Media media = mediaService.upload(file);

		assertNotNull(media);
		assertTrue(new File(media.getUri()).exists());
	}

	@Test
	void test_deleteFile() throws IOException {
		MultipartFile file = getMultipartFile(mockFile, "image/png");
		Media media = mediaService.upload(file);

		assertNotNull(media);

		mediaService.deleteById(media.getId());

		assertFalse(new File(media.getUri()).exists());
	}
}
