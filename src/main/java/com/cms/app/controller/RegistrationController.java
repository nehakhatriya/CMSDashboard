package com.cms.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.app.model.Instructor;
import com.cms.app.model.Role;
import com.cms.app.model.Student;
import com.cms.app.model.User;
import com.cms.app.service.RegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("app/auth")
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/register/instructor")
	public ResponseEntity<?> registerInstructor(@Valid @RequestBody Instructor instructor){
		instructor.setRole(Role.INSTRUCTOR);
		User user=service.createUser(instructor);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	@PostMapping("/register/student")
	public ResponseEntity<?> registerStudent(@Valid @RequestBody Student student){
		student.setRole(Role.STUDENT);
		User user=service.createUser(student);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
}
