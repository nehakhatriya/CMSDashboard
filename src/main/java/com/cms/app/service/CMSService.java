package com.cms.app.service;

import java.util.List;

import com.cms.app.exception.CourseNotFoundException;
import com.cms.app.exception.UserNotFoundException;
import com.cms.app.model.Course;
import com.cms.app.model.Student;

public interface CMSService {

	//student access
	public String enrollCourse(Long courseId,Long studentId) throws UserNotFoundException,CourseNotFoundException;
	public String unenrollCourse(Long courseId,Long studentId) throws UserNotFoundException,CourseNotFoundException;
	public List<Course> browseCourse();
	
	public Course getCourse(Long courseId) throws CourseNotFoundException;
	
	//instructor access
	public  Course createCourse(Course course,Long id) throws UserNotFoundException;
	public String deleteCourse(Long courseId) throws CourseNotFoundException;
	public String updateCourse(Long courseId, Course updatedCourse) throws CourseNotFoundException;
	public List<Student> viewEnrollement(Long courseId)throws CourseNotFoundException;
}
