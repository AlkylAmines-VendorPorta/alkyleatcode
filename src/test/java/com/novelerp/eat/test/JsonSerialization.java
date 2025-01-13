package com.novelerp.eat.test;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerialization {

	public static void main(String[] args) throws Exception{
		testStudentBook();
		testTeacherSubject();
	}
	
	
	public static void testStudentBook() throws Exception{
		Student s1 =  new Student("101","Sumit",24l);
		Student s2 =  new Student("102","Ankush",25l);
		Book b1= new Book("b1", "JAVA","1001",s1);
		Book b2= new Book("b2", ".NET","1002",s1);
		
		Book b3= new Book("b1", "JAVA","1004",s2);
		Book b4= new Book("b2", ".NET","1005",s2);

		List<Book> books = new ArrayList<>();
		books.add(b1);
		books.add(b2);
		List<Book> book2s = new ArrayList<>();
		book2s.add(b3);
		book2s.add(b4);

		s1.setBooks(books);
		s2.setBooks(book2s);
		List<Student> students = new ArrayList<>();
		students.add(s1);
		students.add(s2);

		ObjectCollection col =  new ObjectCollection();
		col.setBooks(books);
/*		col.setStudent(students);
*/		ObjectMapper mapper =  new ObjectMapper();
		String result = mapper.writeValueAsString(col);
		System.out.println(result);

	}
	
	public static void testTeacherSubject() throws Exception{
		
		Subject subject = new Subject(101L, "Java");
		Teacher teacher =  new Teacher(201L,"Ankush", subject);
		subject.setTeacher(teacher);
		ObjectMapper mapper =  new ObjectMapper();
		String result = mapper.writeValueAsString(subject);
		System.out.println(result);

	}
}
