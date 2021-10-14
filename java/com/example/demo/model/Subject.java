package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "subId")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subId;
	
	private String subName;
	private String subCode;
	
	@ManyToMany (mappedBy = "subjects", cascade =  {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Standard> classes = new ArrayList<Standard>();
	
	
	@ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST,
	  CascadeType.REFRESH})	  
	@JoinTable (name = "sub_staff")
 	private List<Instructor> instructors = new ArrayList<Instructor>();
	
	public Subject() {
		
	}
	public Subject(Long subId, String subName, String subCode) {
		super();
		this.subId = subId;
		this.subName = subName;
		this.subCode = subCode;
	}
	
	
	public List<Instructor> getInstructors() {
		return instructors;
	}
	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}
	public List<Standard> getClasses() {
		return classes;
	}
	public void setClasses(List<Standard> classes) {
		this.classes = classes;
	}
	public Long getSubId() {
		return subId;
	}
	public void setSubId(Long subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	
	
}
