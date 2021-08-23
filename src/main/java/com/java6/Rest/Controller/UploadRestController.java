package com.java6.Rest.Controller;

import java.io.File;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.java6.Service.UploadService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1")
public class UploadRestController {
	@Autowired
	UploadService uploadService;
	
	@PostMapping("rest/upload/{folder}")
	public JsonNode upload(@PathParam("file") MultipartFile file,@PathVariable("folder") String folder) {
		File saveFile = uploadService.save(file, folder);
		ObjectMapper mapper =  new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("name", saveFile.getName());
		node.put("size", saveFile.length());
		System.out.println("data" + saveFile);
		return node;
	}
}
