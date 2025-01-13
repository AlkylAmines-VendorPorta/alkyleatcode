package com.novelerp.eat.test;

import java.util.LinkedList;



public class InterviewTest {
	public static String getNode(LinkedList<String> originalList,int ind){
		  
			 return  originalList.get(ind);	
	}

	    public static void main (String args[]) 
	    { 
	    	String input1="My Name is Khan";
	    	String[] str=input1.split(" ");
			System.out.println(str[1]);
			StringBuffer reverseString = null;
			for(int i=str.length-1; i>=0;i--){
				reverseString.append(str[i]);
			}
			System.out.println(reverseString);
	    }
	    	
	} 
