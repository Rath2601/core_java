package com.java8features.predicate;

import java.util.Objects;
import java.util.function.Predicate;

public class PredicateCode {

	void sample(Emp e) {
		Predicate<String> p = i-> (i.length()>4); // sample predicate
		
		Predicate<String> p1 = i-> (i.trim().contains("rath")); // sample predicate
		
		Predicate<String> p2 = i-> (i.endsWith("a")); // sample predicate
		
		Predicate<String> p3 = i-> (i.charAt(2)=='z'); // sample predicate
		
		
		System.out.println("P boolean value :"+p.test("rathna"));
		System.out.println("P1 boolean value :"+p1.test("rathna"));
		System.out.println("P2 boolean value :"+p2.test("rathna"));
		System.out.println("P3 boolean value :"+p3.test("rathna"));
		
		Predicate<String> negatedPredicate = p3.negate();
		
		System.out.println("negatedPredicate boolean value :"+negatedPredicate.test("rathna"));
		
		Predicate<String> predicateCombined =  p.and(p1).or(p2).and(negatedPredicate);
		
		System.out.println("predicateCombined boolean value :"+predicateCombined.test("rathna"));
		
		Predicate<String> p5 = Predicate.isEqual("sample");
		
		System.out.println("P5 boolean value :"+p5.test("rathna"));
		
		Predicate<String> pNot = Predicate.not(p);
		
		System.out.println("pNot boolean value :"+pNot.test("rathna"));
		
		Predicate<Emp> p4 = e1 -> (
				(Objects.equals(e1.getEmpNum(), 2349838))
				&& (e1.getName().length()>4)
				&& (e1.getMail().contains(".com"))
				);
		
		
		System.out.println(p4.test(e));
				
		
	}

	public static void main(String[] args) {
		PredicateCode pc = new PredicateCode();
		Emp e = new Emp();
		e.setEmpNum(2349838);
		e.setName("Rathnasabapathy");
		e.setMail("rathnasabapathy@coder.com");
		pc.sample(e);
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