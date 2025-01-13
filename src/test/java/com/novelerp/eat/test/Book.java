package com.novelerp.eat.test;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/*@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
property="bookId")*/
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5800126469550212497L;
	private String bookId;
	private String name;
	
	private String code;
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
			property="studentId")

	private Student student;

	public Book(){
		
	}
	
	public Book(String bookId, String name, String code, Student student){
		this.bookId = bookId;
		this.name = name;
		this.code =code;
		this.student=student;
	}
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}