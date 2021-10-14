package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Instructor;
import com.example.demo.model.Standard;
import com.example.demo.model.Subject;
import com.example.demo.service.InstructorService;
import com.example.demo.service.SubjectService;

@RestController
@RequestMapping("/standard/subject")
public class SubjectController {

	@Autowired
	SubjectService subService;
	
	@Autowired
	InstructorService insService;
	
	@GetMapping("")  
	public List<Subject> getAll()   
	{  
		return subService.getAllSubjects();  
	}
	
	//Get all subjects for a given Standard
	@GetMapping("{stdId}/subjects")  
	public List<Subject> getSubjectsForStandard(@PathVariable("stdId")Long stdId)   
	{  
		return subService.getSubjectsForStandard(stdId);  
	}
	
	@PostMapping("/add")  
	public ResponseEntity<Subject> add(@RequestBody Subject subject)   
	{  
		return new ResponseEntity<Subject>(subService.addAndUpdate(subject),
				HttpStatus.OK);  
	  
	}  
	
	@GetMapping("/{subId}") 
	public Subject getSubject(@PathVariable("subId")Long subId) { 
		return subService.getSubject(subId); 
	}
	
	@DeleteMapping("/{subId}") 
	private void removeSubject(@PathVariable("subId")Long subId) { 
		 subService.deleteSubject(subId); 
	}
	
	//Update 
	@PutMapping("")  
	public ResponseEntity<Subject> update(@RequestBody Subject std)   
	{  
		return new ResponseEntity<Subject>(subService.addAndUpdate(std),
				HttpStatus.OK);   
	}
	
	@PutMapping("/{subId}/instructor/{instId}")
	public ResponseEntity<Subject> addSubject(@PathVariable("subId")Long subId,
									@PathVariable("instId")Long instId)   
	{  
		boolean isPresent = false;
		
		Subject sub = subService.getSubject(subId);
		
		List<Instructor> instructors = sub.getInstructors();
		if(instructors != null && instructors.size() >0) {
			for(int i = 0; i < instructors.size(); i++) {
				if (instructors.get(i).getInstId() == instId) {
					isPresent = true;
					break;
				}
			}
		}
		
		if(!isPresent) {
			Instructor inst = insService.getInstructorById(instId);
			instructors.add(inst);
			sub.setInstructors(instructors);
		}
		
		return new ResponseEntity<Subject>(subService.addAndUpdate(sub),
				HttpStatus.OK);   
	}
	
	
}
