package com.java8features;import java.util.Random;

public class LambdaFunction implements FInterface{
	
	void test(int c, int d, Emp e) {
		
		FInterface s = new LambdaFunction() ;
		FInterface s1= (String a, String b) -> {
			return b.trim()+a;
		};
		
		s.add(c, d);
		
		System.out.println(s.details(e));
		
		System.out.println(s.show(" Suthan", " Simp"));
			
	}
	
	public static void main(String[] args) {
		LambdaFunction lf = new LambdaFunction();
		Emp e = new Emp();
		e.setEmpNum(2349838);
		e.setName("Rathnasabapathy");
		e.setMail("rathnasabapathy@coder.com");
		
		lf.test(10, 16, e);
	}

	@Override
	public String show(String user, String nickName) {
		// TODO Auto-generated method stub
		return null;
	}
}

@FunctionalInterface
interface FInterface {
	default void add(int a, int b) {
		int c = a+b;
		System.out.println(c);
	}
	
	String show(String user, String nickName);
	
	default String details(Emp e) {
		
			return "Emp [name=" + e.getName() + ", empNum=" + e.getEmpNum() + ", mail=" + e.getMail() + "]";
	}
}

class Emp{
	
	private String name;
	private int empNum;
	private String mail;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEmpNum() {
		return empNum;
	}
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public Emp(String name, int empNum, String mail) {
		super();
		this.name = name;
		this.empNum = empNum;
		this.mail = mail;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Emp() {
		super();
	}

	
}