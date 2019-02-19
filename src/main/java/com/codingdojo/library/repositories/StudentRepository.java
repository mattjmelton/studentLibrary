package com.codingdojo.library.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.library.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
	//get all students
	List<Student> findAll();
	
	Student findByEmail(String email);
}
