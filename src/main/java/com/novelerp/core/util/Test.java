package com.novelerp.core.util;

import java.lang.reflect.Method;
import java.util.Date;

public class Test {

	public String testMethod(){
		
		System.out.println("Test Method");
		return "Hello";
	}
	
	public static void main(String[] args) throws Exception{
			Test ob = new Test();
			Class c = ob.getClass();
			Method tm =  c.getMethod("testMethod", null);
			String hello = (String) tm.invoke(ob, null);
			System.out.println(hello);
	}
}
