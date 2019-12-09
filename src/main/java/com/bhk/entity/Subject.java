package com.bhk.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String teacherName;
	@ManyToMany(mappedBy = "subjects")
	private Set<Student> students = new HashSet<Student>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Student student) {
		this.students.add(student);
	}

	public Subject(String name, String teacherName) {
		this.name = name;
		this.teacherName = teacherName;		
	}

	public Subject() {
	}

}
