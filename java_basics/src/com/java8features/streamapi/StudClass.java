package com.java8features.streamapi;

public class StudClass {
	private Long studId;
	private String name;
	private String location;
	private String gender;
	private Double salary;

	public StudClass() {
		super();
	}

	public StudClass(Long studId, String name, String location, String gender,  Double salary) {
		super();
		this.studId = studId;
		this.name = name;
		this.location = location;
		this.gender = gender;
		this.salary = salary;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Long getStudId() {
		return studId;
	}

	public void setStudId(Long studId) {
		this.studId = studId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGender() {
		return gender;
	}

	@Override
	public String toString() {
		return "StudClass [studId=" + studId + ", name=" + name + ", location=" + location + ", gender=" + gender
				+ ", salary=" + salary + "]";
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
