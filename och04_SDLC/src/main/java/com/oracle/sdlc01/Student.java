package com.oracle.sdlc01;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
//                               생명주기 구현 
public class Student implements InitializingBean, DisposableBean{
	private String name;
	private int age;
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	// 생성자 생성이후 
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Student의  afterPropertiesSet()");
	}
    // 소멸자수행이전 (유언장)
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Student의   destroy()");
	}
	

	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
}
