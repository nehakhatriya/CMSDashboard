package com.cms.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.app.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

}
