package com.cms.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.app.exception.CourseNotFoundException;
import com.cms.app.exception.UserNotFoundException;
import com.cms.app.model.Course;
import com.cms.app.model.Student;
import com.cms.app.service.CMSService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("app/v1")
public class CMSRepository {
	
	@Autowired
	private CMSService service;
	
	@PostMapping("/create")
	public ResponseEntity<?> createCourse(@Valid @RequestBody Course course, @RequestHeader(required=true) Long id) throws UserNotFoundException{
		if(id==null) {
			throw new IllegalArgumentException("header 'id' is missing");
		}
		Course created=service.createCourse(course,id);
		return new ResponseEntity<>(created,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> createCourse(@PathVariable Long id) throws CourseNotFoundException{
		
		String response=service.deleteCourse(id);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/enrolled/{id}")
	public ResponseEntity<?> viewEnrollement(@PathVariable("id") Long courseId) throws CourseNotFoundException{
		
		List<Student> enrolled=service.viewEnrollement(courseId);
		return new ResponseEntity<>(enrolled,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCourse(@PathVariable("id") Long courseId, @RequestBody Course updatedCourse) throws CourseNotFoundException{
		String response=service.updateCourse(courseId,updatedCourse);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/unenroll/{id}")
	public ResponseEntity<?> unenrollCourse(@PathVariable("id") Long courseId, @RequestHeader(required=true) Long studentId)throws UserNotFoundException,CourseNotFoundException{
		if(studentId==null) {
			throw new IllegalArgumentException("header 'id' is missing");
		}
		String response=service.unenrollCourse(courseId, studentId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/enroll/{id}")
	public ResponseEntity<?> enrollCourse(@PathVariable("id") Long courseId, @RequestHeader(required=true) Long studentId)throws UserNotFoundException,CourseNotFoundException{
		if(studentId==null) {
			throw new IllegalArgumentException("header 'id' is missing");
		}
		String response=service.enrollCourse(courseId, studentId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/courses")
	public ResponseEntity<?> getAllCourses(){
		List<Course> repsonse = service.browseCourse();
		return new ResponseEntity<>(repsonse,HttpStatus.OK);
	}
	
	@GetMapping("/course/{id}")
	public ResponseEntity<?> getCourse(@PathVariable Long id) throws CourseNotFoundException{
		Course repsonse = service.getCourse(id);
		return new ResponseEntity<>(repsonse,HttpStatus.OK);
	}
	
	
}
