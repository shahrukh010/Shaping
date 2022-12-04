package com.shaping.code.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public static void uploadFile(String pathDir, String fileName, MultipartFile multipartFile) throws IOException {

		Path uploadPath = Paths.get(pathDir);

		if (!Files.exists(uploadPath)) {

			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			InputStream inputStream = multipartFile.getInputStream();
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("could not save file: " + fileName);
		}

	}

}
