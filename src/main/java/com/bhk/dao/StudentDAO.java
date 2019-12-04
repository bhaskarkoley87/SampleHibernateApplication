package com.bhk.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bhk.entity.Address;
import com.bhk.entity.Student;
import com.bhk.utils.HibernateUtil;

public class StudentDAO {
	private HibernateUtil hibernateUtil;
	private Transaction transaction = null;

	public StudentDAO() {
		hibernateUtil = HibernateUtil.getInstance();
	}

	public boolean save(Student student, Address address) {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// save the student object
			session.saveOrUpdate(address);
			session.save(student);

			// commit transaction
			transaction.commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public List<Student> getStudents() {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Student", Student.class).list();
		}
	}

	/*@NamedQuery(name="GET_STUDENT_BY_FIRST_NAME", query="SELECT s FROM Student s WHERE s.firstName :frstName")
	public Student getStudentByFirstName(String firstName) {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {
			
		}
	}*/

}
