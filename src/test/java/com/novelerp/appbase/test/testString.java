package com.novelerp.appbase.test;

public class testString {

	public static void main(String[] args) {
		{
			String xxx="{\"name1\":\"xyz\",\"lifnr\":null,\"street\":null,\"street2\":null,\"street3\":null}";
			xxx=xxx.replaceAll("null", "\"\"");
			System.out.println(xxx);
		}
	}

}
