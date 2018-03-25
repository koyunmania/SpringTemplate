package com.spring.template.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.template.errorhandling.exceptions.StorageFileNotFoundException;
import com.spring.template.storage.StorageService;

@Controller
public class FileUploadController {
	
	private final StorageService storageService;
	
	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {
		// storageService.loadAll().map(arg0)
		return "";
	}
}
