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

import com.example.demo.model.Standard;
import com.example.demo.model.Subject;
import com.example.demo.service.StandardService;
import com.example.demo.service.SubjectService;

@RestController
@RequestMapping("/standard")
public class StandardController {

	@Autowired
	StandardService stdService;
	
	@Autowired
	SubjectService subService;
	
	@GetMapping("")  
	public List<Standard> getAll()   
	{  
		return stdService.getAllStandards();  
	}  
	
	@PostMapping("/add")  
	public ResponseEntity<Standard> add(@RequestBody Standard std)   
	{  
		return new ResponseEntity<Standard>(stdService.addAndUpdate(std),
				HttpStatus.CREATED);  
	  
	}  
	
	@GetMapping("/{stdId}") 
	public Standard getStandard(@PathVariable("stdId")Long stdId) { 
		return stdService.getStandard(stdId); 
	}
	
	@DeleteMapping("/{stdId}") 
	private void removeStandard(@PathVariable("stdId")Long stdId) { 
		 stdService.deleteStandard(stdId); 
	}
	
	//Update 
	@PutMapping("")  
	public ResponseEntity<Standard> update(@RequestBody Standard std)   
	{  
		return new ResponseEntity<Standard>(stdService.addAndUpdate(std),
				HttpStatus.OK);   
	}
	
	//Update - Add a subject if not already added to Standard
	@PutMapping("/{stdId}/subject/{subId}")
	public ResponseEntity<Standard> addSubject(@PathVariable("stdId")Long stdId,
									@PathVariable("subId")Long subId)   
	{  
		boolean isPresent = false;
		
		Standard std = stdService.getStandard(stdId);
		
		List<Subject> subjects = std.getSubjects();
		if(subjects != null && subjects.size() >0) {
			for(int i = 0; i < subjects.size(); i++) {
				if (subjects.get(i).getSubId() == subId) {
					isPresent = true;
					break;
				}
			}
		}
		
		if(!isPresent) {
			Subject sub = subService.getSubject(subId);
			subjects.add(sub);
			std.setSubjects(subjects);
		}
		
		return new ResponseEntity<Standard>(stdService.addAndUpdate(std),
				HttpStatus.OK);   
	}
}
