package com.spring.template.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	void init();
	void store(MultipartFile file);
	Stream<Path> loadAll();
}
