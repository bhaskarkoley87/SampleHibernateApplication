package com.bhk.app;

import javax.persistence.CascadeType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bhk.dao.GuideDAO;
import com.bhk.dao.StudentDAO;
import com.bhk.entity.Address;
import com.bhk.entity.Guide;
import com.bhk.entity.Student;
import com.bhk.utils.HibernateUtil;

public class Application {

	public static void main(String[] args) throws Exception{
		Address address1 = new Address("ABC Street", "Opp. xyz", "Kolkata", "West Bengal", "123810");
		Address address2 = new Address("DEF Street", "Opp. abc", "Kolkata", "West Bengal", "123899");
		Guide guide = new Guide("SF13942", "Ramesh Kumar", 100000);	
		Student student = new Student(2l, "Bhaskar", "koley", address1, address2, guide);		
		HibernateUtil hibernateUtil = HibernateUtil.getInstance();
		Transaction transaction = null;
		try (Session session = hibernateUtil.getSessionFactory().getCurrentSession()) {
			transaction = session.beginTransaction();
			
			// Use the save function to create the rows in the DB. 
			// But you need to call the save function for all the objects, you want to save.
			//session.save(guide);
			//session.save(student);
			
			// You can use the persist method to save the the related objects by applying CascadeType.PERSIST option.
			session.persist(student);
			transaction.commit();
		}catch (Exception ex) {
			ex.printStackTrace();			
		}

	}

}
