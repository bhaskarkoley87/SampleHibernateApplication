package com.bhk.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	private Long rollNo;
	private String firstName;
	private String lastName;
	private List<Address> studentAddress;

	public Long getRollNo() {
		return rollNo;
	}

	public void setRollNo(Long rollNo) {
		this.rollNo = rollNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Address> getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(List<Address> studentAddress) {
		this.studentAddress = studentAddress;
	}

	public Student(Long rollNo, String firstName, String lastName, List<Address> studentAddress) {
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentAddress = studentAddress;
	}

	public Student() {
	}
}
