package com.oracle.DI03;

public class Student {

	private String name;
	private String age;
	private String gradeNum;
	private String classNum;
	
	public Student(String name, String age, String gradeNum, String classNum) {
		System.out.println("1. Student 생성자->"+name);
		this.name = name;
		this.age =  age;
		this.gradeNum = gradeNum;
		this.classNum = classNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("2. Student setName->"+name);
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGradeNum() {
		return gradeNum;
	}

	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
}
