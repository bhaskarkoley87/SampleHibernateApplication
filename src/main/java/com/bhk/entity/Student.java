package com.bhk.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.bhk.utils.StudentStatus;


@Entity
public class Student {

	@Id
	private Long rollNo;
	private String firstName;
	private String lastName;
	// Use to embed a class(custom data type) as a value type
	@Embedded
	// Use to map same custom value type (class) for multiple instance
	@AttributeOverrides({ @AttributeOverride(name = "addressLine1", column = @Column(name = "current_address_line_1")), // use
																														// to
																														// map
																														// member
																														// variable
																														// to
																														// table
																														// column
																														// name.
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

	// Cascade with PERSIST value is use to make single save for all the related
	// objects.
	@ManyToOne(cascade = { CascadeType.PERSIST })
	// By Default in ManyToOne relationship fetch=FetchType.EGER, to change it we
	// have to add the parameter fetch=FetchType.LAZY in the @ManyToOne
	/*
	 * Fetch Type according to JPA 2.0 Spec. OneToMany = LAZY ManyToOne = EGER
	 * ManyToMany = LAZY OneToOne = EGER
	 */
	// This is use to map the mapping column of between two tables.
	@JoinColumn(name = "guide_id")
	private Guide guide;

	// This is use to map the ENUM to a table column
	@Enumerated(EnumType.STRING)
	private StudentStatus studentStatus;

	// This is use map a collection object in a separate table.
	@ElementCollection
	@CollectionTable(name = "ExtracurricularActivity", joinColumns = @JoinColumn(name = "rollNo"))
	private Collection<String> extracurricular_Activity = new ArrayList<String>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "student_subject", joinColumns = { @JoinColumn(name = "sudent_id") }, inverseJoinColumns = {
			@JoinColumn(name = "subject_id") })
	private Set<Subject> subjects = new HashSet<Subject>();

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

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Subject subject) {
		this.subjects.add(subject);
	}

	public StudentStatus getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(StudentStatus studentStatus) {
		this.studentStatus = studentStatus;
	}

	public Collection<String> getExtracurricular_Activity() {
		return extracurricular_Activity;
	}

	public void setExtracurricular_Activity(Collection<String> extracurricular_Activity) {
		this.extracurricular_Activity = extracurricular_Activity;
	}

	public Student(Long rollNo, String firstName, String lastName, Address studentAddress, Address studentPerAddress,
			Guide guide, Subject subject, StudentStatus studentStatus, Collection<String> extracurricular_Activity) {
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentAddress = studentAddress;
		this.studentPerAddress = studentPerAddress;
		this.guide = guide;
		this.studentStatus = studentStatus;
		this.extracurricular_Activity = extracurricular_Activity;
		this.subjects.add(subject);
	}

	public Student() {
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (this.rollNo.equals(other.rollNo) && this.firstName.equals(other.firstName))
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
