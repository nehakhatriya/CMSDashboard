package com.cms.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long courseId;
	
	@NotBlank(message = "Title cannot be empty")
	@Column(nullable = false, length = 255)
	private String title;
	
	@Column(nullable = false, length = 255)
	private String desription;
	
	@ManyToOne
	@JoinColumn(name = "instructor_id")
    private Instructor instructor;

	@ManyToMany(mappedBy = "enrolledCourses")
    private List<Student> students = new ArrayList<>();
	
	@Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
	
	
	
}
