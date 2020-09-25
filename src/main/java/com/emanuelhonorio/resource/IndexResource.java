package com.emanuelhonorio.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexResource {
	
	
	@GetMapping
	public ResponseEntity<?> index() {
		return ResponseEntity.ok().body("Api is running");
	}
}
