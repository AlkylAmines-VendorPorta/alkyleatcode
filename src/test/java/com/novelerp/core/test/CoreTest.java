package com.novelerp.core.test;

public class CoreTest {

	public static void main(String[] args) {
		String fileName = "demo.txt";
		int index = fileName.lastIndexOf(".");
		String  extension =  fileName.substring(index+1);
		System.out.println(extension+ " " + index);
	}
}
