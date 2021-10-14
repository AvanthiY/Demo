package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "instId")
public class Instructor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long instId;
	
	private String name;
	private String phoneNum;
	
//	@JsonProperty
	private boolean isActive;
//	@JsonProperty
	private boolean isDeleted;
	
	@ManyToMany (mappedBy = "instructors", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Subject> subjects = new ArrayList<>();

	public Instructor() {
		
	}
	public Instructor(Long instId, String name, String phoneNum) {
		super();
		this.instId = instId;
		this.name = name;
		this.phoneNum = phoneNum;
	}
	public Long getInstId() {
		return instId;
	}
	public void setInstId(Long instId) {
		this.instId = instId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public List<Subject> getSubjects() { 
	  return subjects; 
	} 
	public void setSubjects(List<Subject> subjects) { 
		this.subjects = subjects;
	}
	 
	

}
