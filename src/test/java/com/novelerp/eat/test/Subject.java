package com.novelerp.eat.test;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
property="subjectId")
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8998630844170252603L;
	private Long subjectId;
	private String name;
	private Teacher teacher;
	
	public Subject(){
		
	}
	public Subject(Long subjectId, String name){
	
		this.subjectId = subjectId;
		this.name =  name;
	}
	
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
