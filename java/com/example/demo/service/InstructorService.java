package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Instructor;
import com.example.demo.model.Subject;
import com.example.demo.repository.InstructorRepository;
import com.example.demo.repository.StandardRepository;
import com.example.demo.repository.SubjectRepository;

@Service
public class InstructorService {
	
	@Autowired
	InstructorRepository instRepo;

	@Autowired
	StandardRepository stdRepo;

	//Get all instructors
	public List<Instructor> getAllInstructors() {
		List<Instructor> instructors = new ArrayList<>();
		instRepo.findAll().forEach(instructors::add);
		return instructors;
	}

	//To create and update
	public Instructor addAndUpdate(Instructor inst) {
		return instRepo.save(inst);
	}

	//Get instructors by id
	public Instructor getInstructorById(Long id) {
		return instRepo.findByInstId(id);
	}

	//Delete instructor with given id
	public void deleteInstructor(Long subId) {
		instRepo.deleteById(subId);
		
	}

	//Get all subjects for a given standard id
	public List<Instructor> getInstructorForSubject(Long subId) {
		//Not Working -- Exception
		/*
		 * List<Subject> subjects = new ArrayList<>(); subjects =
		 * stdRepo.getById(stdId).getSubjects(); return subjects;
		 */
		
		 //Defined a new method in Repo interface to get all Subjects 
		 // for a given Standard. Works.
		List<Instructor> instructors = new ArrayList<>();
		instRepo.findBySubjectsSubId(subId).forEach(instructors :: add);
		return instructors;
	}


}
