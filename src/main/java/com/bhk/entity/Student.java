package com.bhk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	private Long rollNo;
	private String firstName;
	private String lastName;
	@OneToOne
	@JoinColumn(name="id")
	private Address studentAddress;

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

	public Address getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(Address studentAddress) {
		this.studentAddress = studentAddress;
	}

	public Student(Long rollNo, String firstName, String lastName, Address studentAddress) {
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentAddress = studentAddress;
	}

	public Student() {
	}
}
