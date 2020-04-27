package com.oracle.DI01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
	//	String configLocation = "applicationCTX.xml";
	//	String configLocation = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext("applicationCTX.xml");
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class);

		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
		
	}
	
}
