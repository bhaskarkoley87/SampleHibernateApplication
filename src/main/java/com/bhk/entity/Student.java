package com.bhk.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student {

	@Id
	private Long rollNo;
	private String firstName;
	private String lastName;
	// Use to embed a class(custom data type) as a value type
	@Embedded
	// Use to map same custom value type (class) for multiple instance
	@AttributeOverrides({ @AttributeOverride(name = "addressLine1", column = @Column(name = "current_address_line_1")), // use to map member variable to table column name.
			@AttributeOverride(name = "addressLine2", column = @Column(name = "current_address_line_2")),
			@AttributeOverride(name = "city", column = @Column(name = "current_city")),
			@AttributeOverride(name = "state", column = @Column(name = "current_state")),
			@AttributeOverride(name = "zip", column = @Column(name = "current_zip")) })
	private Address studentAddress;
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "addressLine1", column = @Column(name = "permanent_address_line_1")),
			@AttributeOverride(name = "addressLine2", column = @Column(name = "permanent_address_line_2")),
			@AttributeOverride(name = "city", column = @Column(name = "permanent_city")),
			@AttributeOverride(name = "state", column = @Column(name = "permanent_state")),
			@AttributeOverride(name = "zip", column = @Column(name = "permanent_zip")) })
	private Address studentPerAddress;

	// Cascade with PERSIST value is use to make single save for all the related objects.
	@ManyToOne(cascade= {CascadeType.PERSIST}) 
	// This is use to map the mapping column of between two tables.
	@JoinColumn(name="guide_id")
	private Guide guide;

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

	public Address getStudentPerAddress() {
		return studentPerAddress;
	}

	public void setStudentPerAddress(Address studentPerAddress) {
		this.studentPerAddress = studentPerAddress;
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	public Student(Long rollNo, String firstName, String lastName, Address studentAddress, Address studentPerAddress,
			Guide guide) {
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentAddress = studentAddress;
		this.studentPerAddress = studentPerAddress;
		this.guide = guide;
	}

	public Student() {
	}
}
