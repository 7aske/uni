package com.example.backend.service.impl;

import com.example.backend.entity.Media;
import com.example.backend.entity.Post;
import com.example.backend.exception.io.MediaUploadException;
import com.example.backend.repository.MediaRepository;
import com.example.backend.service.MediaService;
import com.example.backend.util.IOUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class MediaServiceImpl implements MediaService {
	private final MediaRepository mediaRepository;

	@Value("${media.upload.path}")
	private String mediaUploadPath;

	@Override
	public List<Media> findAll() {
		return mediaRepository.findAll();
	}

	@Override
	public Media findById(Integer mediaId) {
		return mediaRepository.findById(mediaId)
				.orElseThrow(() -> new NoSuchElementException("MediaService.notFound"));
	}

	@Override
	public Media save(Media media) {
		return mediaRepository.save(media);
	}

	@Override
	public Media update(Media media) {
		return mediaRepository.save(media);
	}

	@Override
	public void deleteById(Integer mediaId) {
		Media media = mediaRepository.findById(mediaId)
				.orElseThrow(() -> new NoSuchElementException("media.not-found"));
		Path path = Paths.get(mediaUploadPath, media.getUri());
		IOUtils.deleteFileIfExistsNoExcept(path);
		mediaRepository.deleteById(mediaId);
	}

	@Override
	public Media upload(MultipartFile file) {
		if (file.isEmpty()) throw new MediaUploadException("media.upload.file-empty");

		String filename = getUploadedFilename(file);
		String contentType = file.getContentType();
		Path path = Paths.get(mediaUploadPath,"static/media", filename);
		String pathStr = path.toString();
		String uri = Paths.get("static/media", filename).toString();

		Optional<Media> existing = mediaRepository.findByUri(uri);
		if (existing.isPresent()) return existing.get();

		try {
			BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));

			File destFile = new File(pathStr);
			destFile.getParentFile().mkdirs();
			ImageIO.write(src, contentType == null ? "png" : contentType.split("/")[1], destFile);

			Media media = new Media();
			media.setUri(uri);
			return mediaRepository.save(media);
		} catch (IOException e) {
			e.printStackTrace();
			IOUtils.deleteFileIfExistsNoExcept(path);
			throw new MediaUploadException("media.upload.failed");
		}
	}

	private String getUploadedFilename(MultipartFile file) {
		String uuid = UUID.randomUUID().toString();
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		return String.format("%s.%s", uuid, ext);
	}
}