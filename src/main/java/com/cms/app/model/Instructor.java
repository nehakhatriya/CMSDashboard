package com.cms.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor implements User{
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank(message = "Name cannot be empty")
	@Column(nullable = false, length = 255)
	private String name;
	
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	@Column(unique = true, nullable = false, length = 255)
	private String email;
	
	@NotBlank(message = "Password cannot be empty")
	@Column(nullable = false, length = 255)
    private String password;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

	@Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
	
	@OneToMany(mappedBy="instructor", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Course> courses = new ArrayList<>();
	
}
