package com.cms.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.app.model.Instructor;
import com.cms.app.model.Student;
import com.cms.app.model.User;
import com.cms.app.repository.CourseRepository;
import com.cms.app.repository.InstructorRepository;
import com.cms.app.repository.StudentRepository;

@Service
public class RegistrationService {

	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public User createUser(Instructor instructor) {
		// TODO Auto-generated method stub
		Instructor inst=instructorRepository.save(instructor);
		return inst;
	}
	
	public User createUser(Student student) {
		return studentRepository.save(student);
	}

}
