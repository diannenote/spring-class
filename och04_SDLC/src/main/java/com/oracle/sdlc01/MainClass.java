package com.oracle.sdlc01;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
public class MainClass {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = 	new GenericXmlApplicationContext();
	//	AbstractApplicationContext ctx = 	new GenericXmlApplicationContext();
        System.out.println("1 ctx.load  Before"); 
		ctx.load("classpath:applicationCTX01.xml");
        System.out.println("2 ctx.load  After");
		// 실제 Bean 생성
		ctx.refresh();
        System.out.println("3 ctx.refresh  After");
		// 객체 종료(컨테이너 종료 -> 다른 용어로 빈 팩토리(Bean Factory), 애플리케이션 컨텍스트(Application Context))
 		ctx.close();
        System.out.println("4 ctx.close  After");
	}
}
