package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{
	
	public Subject findBySubId(Long subId);
	
	public List<Subject> findByClassesStdId(Long stdId);

}
