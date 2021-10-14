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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "stdId")
public class Standard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stdId;
	
	private String stdName;
	private String stdSection;
	
	@ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable (name = "std_sub")
	private List<Subject> subjects = new ArrayList<Subject>();
	
	public Standard() {
		
	}
	public Standard(Long stdId, String stdName, String stdSection) {
		super();
		this.stdId = stdId;
		this.stdName = stdName;
		this.stdSection = stdSection;
	}
	
	
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public Long getStdId() {
		return stdId;
	}
	public void setStdId(Long stdId) {
		this.stdId = stdId;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public String getStdSection() {
		return stdSection;
	}
	public void setStdSection(String stdSection) {
		this.stdSection = stdSection;
	}
	
	
}
