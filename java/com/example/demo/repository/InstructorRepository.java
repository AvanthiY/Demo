package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
	
	public Instructor findByInstId(Long subId);
	
	public List<Instructor> findBySubjectsSubId(Long stdId);

}
