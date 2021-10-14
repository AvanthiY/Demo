package com.example.demo.controller;

import java.util.ArrayList;
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
import com.example.demo.service.InstructorService;

@RestController
@RequestMapping("/subject")
public class InstructorController {
	
	@Autowired
	InstructorService instService;

	
	//Get all instructors
	@GetMapping("")  
	public List<Instructor> getAll()   
	{  
		return checkIfDeleted(instService.getAllInstructors());  
	}
	
	//Get all Instructors for a subject
	@GetMapping("/{subId}/instructors")  
	public List<Instructor> getInstructorsForSubject(@PathVariable("subId")Long subId)   
	{  
		List<Instructor> instructors = checkIfDeleted(
				instService.getInstructorForSubject(subId));
		
		return instructors;
	}
	
	private List<Instructor> checkIfDeleted(List<Instructor> instructors){
		List<Instructor> toReturn = new ArrayList<>();
		if(instructors != null && instructors.size() >0) {
			for(int i = 0; i < instructors.size(); i++) {
				if (!instructors.get(i).getIsDeleted()) {
					toReturn.add(instructors.get(i));
				}
			}
		}
		return toReturn;
	}
 	
	@PostMapping("/instructor/add")  
	public ResponseEntity<Instructor> add(@RequestBody Instructor inst)   
	{  
		return new ResponseEntity<Instructor>(instService.addAndUpdate(inst),
				HttpStatus.OK);  
	  
	}  

	//Get instructor by id
	@GetMapping("/instructor/{instId}") 
	public Instructor getIntructor(@PathVariable("instId")Long instId) { 
		return instService.getInstructorById(instId); 
	}
	
	@DeleteMapping("/instructor/{instId}") 
	private void removeInstructor(@PathVariable("subId")Long instId) { 
		instService.deleteInstructor(instId); 
	}
	
	//Update 
	@PutMapping("")  
	public ResponseEntity<Instructor> update(@RequestBody Instructor inst)   
	{  
		return new ResponseEntity<Instructor>(instService.addAndUpdate(inst),
				HttpStatus.OK);   
	}

}
