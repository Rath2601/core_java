package com.java8features.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class PredicatePractice {

	static List<Integer> evenInt() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		numbers.add(6);
		numbers.add(7);
		numbers.add(8);
		numbers.add(9);
		numbers.add(10);

		Predicate<Integer> p = i -> (i % 2 == 0);

		for (int i =0; i<numbers.size(); i++) {
			if (!p.test(numbers.get(i))) {
				numbers.remove(i);
			}
		}
		return numbers;
	}
	
	static List<String> lengthCheck(){
		 List<String> names = Arrays.asList("John", "Janister", "Tom", "Jerry");
		 
		 List<String> filteredNames = names.stream().filter(name -> (name.length() > 5)).toList();
		 
		return filteredNames;
	}
	
	static List<String> nullCheck(){
		 List<String> names = Arrays.asList("newyork", "sydney", "london", "mumbai", null, "chennai", null);
		 
		 List<String> filteredNames = names.stream().filter(name -> (!Objects.isNull(name))).toList();
		 
		return filteredNames;
	}
	
	
	static List<String> palindromeCheck(){
		 List<String> names = Arrays.asList("master", "madam", "quick", "racecar","rathna", null);
		 
		 List<String> filteredNames = names.stream().filter(name -> Objects.equals(name,  reverseString(name)) && !Objects.isNull(name)).toList();
		 
		return filteredNames;
	}
	
	static String reverseString(String word) {
		if(!Objects.isNull(word)) {
			if(word.length()==0) {
				return word;
			}
			return reverseString(word.substring(1)) + word.charAt(0);
		}else {
			return null;
		}
	}
	
	static List<Emp2> findEmp(){
		List<Emp2> employeeList = Arrays.asList(
				new Emp2("suthan","suthan1@coder.com",  2318393, "C3B", 2200000d),
				new Emp2("sedhu", "sedhu2@coder.com", 2324241,"C3B", 2500000d),
				new Emp2("naveen", "naveen3@coder.com", 2562451,"C2B", 1800000d),
				new Emp2("rathna", "rathna1@coder.com",1931314, "C3B", 3200000d)
);
		List<Emp2> filterEmp = employeeList.stream()
				.filter(employee -> Objects.equals(employee.getGrade(), "C3B") && (employee.getSalary() > 3000000d)).toList();
		
		return filterEmp;
	}

	public static void main(String[] args) {
		System.out.println(evenInt());
		System.out.println(lengthCheck());
		System.out.println(nullCheck());
		System.out.println(palindromeCheck());
		System.out.println(findEmp());
	}
}

class Emp2{
	private String name;
	private String mail;
	private Integer empId;
	private String grade;
	private Double salary;
	public Emp2(String name, String mail, Integer empId, String grade, Double salary) {
		super();
		this.name = name;
		this.mail = mail;
		this.empId = empId;
		this.grade = grade;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Double getSalary() {
		return salary;
	}
	@Override
	public String toString() {
		return "Emp2 [name=" + name + ", mail=" + mail + ", empId=" + empId + ", grade=" + grade + ", salary=" + salary
				+ "]";
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
}
