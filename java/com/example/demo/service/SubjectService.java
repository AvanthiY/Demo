package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Subject;
import com.example.demo.repository.StandardRepository;
import com.example.demo.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	SubjectRepository subRepo;

	@Autowired
	StandardRepository stdRepo;

	//Get all Subjects
	public List<Subject> getAllSubjects() {
		List<Subject> subs = new ArrayList<>();
		subRepo.findAll().forEach(subs::add);
		return subs;
	}

	//To create and update
	public Subject addAndUpdate(Subject sub) {
		return subRepo.save(sub);
	}

	//Get subject by subject id
	public Subject getSubject(Long subId) {
		return subRepo.findBySubId(subId);
	}

	//Delete subject with given id
	public void deleteSubject(Long subId) {
		subRepo.deleteById(subId);
		
	}

	//Get all subjects for a given standard id
	public List<Subject> getSubjectsForStandard(Long stdId) {
		//Not Working -- Exception
		/*
		 * List<Subject> subjects = new ArrayList<>(); subjects =
		 * stdRepo.getById(stdId).getSubjects(); return subjects;
		 */
		
		 //Defined a new method in Repo interface to get all Subjects 
		 // for a given Standard. Works.
		List<Subject> subjects = new ArrayList<>();
		subRepo.findByClassesStdId(stdId).forEach(subjects :: add);
		return subjects;
	}

}
