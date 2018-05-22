package com.spring.template.api;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.template.errorhandling.exceptions.ExceptionHandling;
import com.spring.template.service.ServiceResult;
import com.spring.template.service.StorageService;

@RestController
public class FileUploadRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadRestController.class);
	
	@Autowired
	private StorageService storageService;
	
	@RequestMapping(value = "/api/fileupload",
			method = RequestMethod.POST,
			consumes = "multipart/form-data",
			produces = "application/json")
	@ResponseBody
	public ServiceResult fileUpload(@RequestParam("file") MultipartFile file) {
		ServiceResult result = new ServiceResult ();
		if(storageService.load(file.getOriginalFilename()).toFile().exists()) {
			result.setMessage("File already existing.");
			result.setStatus(false);
			return result;
		} else {
			storageService.store(file);
			result.setMessage("File successfully uploaded!");
			result.setStatus(true);
			return result;
		}
	}
	
	@RequestMapping(value="/api/fileupload",
			method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public ServiceResult getUploadedFiles() {
		ServiceResult result = new ServiceResult ();
		result.setData(
				storageService.loadAll()
				.map(file -> file.getFileName())
				.collect(Collectors.toList())
				);
		result.setStatus(true);
		result.setMessage("Files returned successfully");
		return result;
	}
	
    @ExceptionHandler
    public ServiceResult fileUploadException(Exception ex) {
    	logger.error(ex.getMessage());
        return ExceptionHandling.returnResponse(ex);
    }
}
