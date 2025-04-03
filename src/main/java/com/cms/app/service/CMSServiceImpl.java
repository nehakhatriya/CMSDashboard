package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.app.exception.CourseNotFoundException;
import com.cms.app.exception.UserNotFoundException;
import com.cms.app.model.Course;
import com.cms.app.model.Instructor;
import com.cms.app.model.Student;
import com.cms.app.repository.CourseRepository;
import com.cms.app.repository.InstructorRepository;
import com.cms.app.repository.StudentRepository;

@Service
public class CMSServiceImpl implements CMSService{
	
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	//create a course
	@Override
	public Course createCourse(Course course,Long id) throws UserNotFoundException {
		Instructor instructor = instructorRepository.findById(id)
	            .orElseThrow(() -> new UserNotFoundException("Instructor Not found"));  //throw exception if instructor id not found
		course.setInstructor(instructor);
		return courseRepository.save(course);
	}
	
	//delete a course
	@Override
	public String deleteCourse(Long courseId) throws CourseNotFoundException{
		Course course = courseRepository.findById(courseId)
	            .orElseThrow(() -> new CourseNotFoundException("Course Not found")); 
		courseRepository.delete(course);
		return "Course Deleted";
	}
	
	//retrieve all enrolled students for a course
	@Override
	public List<Student> viewEnrollement(Long courseId) throws CourseNotFoundException {
			Course course = courseRepository.findById(courseId)
		            .orElseThrow(() -> new CourseNotFoundException("Course Not found"));
			return course.getStudents();
		}
	
	//update the course
		@Override
		public String updateCourse(Long courseId, Course updatedCourse) throws CourseNotFoundException{
			Course existingCourse = courseRepository.findById(courseId)
	                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
			if (updatedCourse.getTitle() != null) {
	            existingCourse.setTitle(updatedCourse.getTitle());
	        }
	        if (updatedCourse.getDesription() != null) {
	            existingCourse.setDesription(updatedCourse.getDesription());
	        }
	        courseRepository.save(existingCourse);
	        return "Updated Course";
		}
		
	//enroll in a course
	@Override
	public String enrollCourse(Long courseId,Long studentId)throws UserNotFoundException,CourseNotFoundException {
		Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFoundException("Student not found"));
        
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        if (!student.getEnrolledCourses().contains(course)) {
            student.getEnrolledCourses().add(course);
            studentRepository.save(student);
            return "Student enrolled successfully!";
        } else {
            return "Student is already enrolled in this course.";
        }
	}
	
	//unenroll in a course
	@Override
	public String unenrollCourse(Long courseId,Long studentId)throws UserNotFoundException,CourseNotFoundException {
		Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        if (student.getEnrolledCourses().contains(course)) {
            student.getEnrolledCourses().remove(course);
            studentRepository.save(student);
            return "Student unenrolled successfully!";
        } else {
            return "Student was not enrolled in this course.";
        }
	}
	
	//retreive all course 
	@Override
	public List<Course> browseCourse() {
		return courseRepository.findAll();
	}
	
	//retreive a course	information
	@Override
	public Course getCourse(Long courseId) throws CourseNotFoundException{
		Course course = courseRepository.findById(courseId)
	            .orElseThrow(() -> new CourseNotFoundException("Course Not found")); 
		return course;
	}
	
	
	
	

}
