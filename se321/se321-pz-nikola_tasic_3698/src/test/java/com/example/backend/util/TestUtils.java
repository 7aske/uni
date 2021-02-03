package com.example.backend.util;

import com.example.backend.entity.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils {
	public static User getUser() {
		User user = new User();
		user.setDisplayName("display_name");
		user.setUsername("username");
		user.setPassword("password");
		user.setEmail("email");
		user.setFirstName("first_name");
		user.setLastName("last_name");
		return user;
	}

	public static Role getRole() {
		Role role = new Role();
		role.setName("role");
		return role;
	}

	public static Post getPost() {
		Post post = new Post();
		post.setTitle("title");
		post.setBody("body");
		post.setSlug("post");
		post.setExcerpt("excerpt");
		return post;
	}

	public static Category getCategory() {
		Category category = new Category();
		category.setName("category");
		return category;
	}

	public static Tag getTag() {
		Tag tag = new Tag();
		tag.setName("tag");
		return tag;
	}

	public static MultipartFile getMultipartFile(File file, String contentType) throws IOException {
		byte[] content = Files.readAllBytes(Paths.get(file.getPath()));
		return new MockMultipartFile(file.getName(), file.getName(), contentType, content);
	}

	public static void createMockImage(File file) throws IOException {
		byte[] aByteArray = {0xa,0x2,0xf,(byte)0xff,(byte)0xff,(byte)0xff};
		int width = 1;
		int height = 2;

		DataBuffer buffer = new DataBufferByte(aByteArray, aByteArray.length);

		WritableRaster raster = Raster.createInterleavedRaster(buffer, width, height, 3 * width, 3, new int[] {0, 1, 2}, null);
		ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		BufferedImage image = new BufferedImage(cm, raster, true, null);

		ImageIO.write(image, "png", file);
	}
}
