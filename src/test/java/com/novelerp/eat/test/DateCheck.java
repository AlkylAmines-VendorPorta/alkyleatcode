/**
 * @author Ankush
 */
package com.novelerp.eat.test;

import java.util.Date;

public class DateCheck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date dt=new Date();
		dt.setDate(14);
		dt.setMonth(04);
		dt.setHours(12);
		dt.setMinutes(00);
		dt.setSeconds(0);
		System.out.println(dt);
		System.out.println(dt.getTime());
	}

}
