package com.novelerp.eat.test;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
property="teacherId")
public class Teacher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1370774983211210574L;
	private Long teacherId;
	private String name;
	private Subject subject;
	
	public Teacher(){
		
	}
	public Teacher(Long teacherId, String name,Subject subject){
		this.teacherId = teacherId;
		this.name = name;
		this.subject =subject;
	}
	
	
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	
}
