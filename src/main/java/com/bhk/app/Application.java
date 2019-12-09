package com.bhk.app;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bhk.entity.Address;
import com.bhk.entity.Guide;
import com.bhk.entity.Student;
import com.bhk.utils.HibernateUtil;
import com.bhk.utils.StudentStatus;

public class Application {

	public static void main(String[] args) throws Exception{
		Address address1 = new Address("ABC Street", "Opp. xyz", "Kolkata", "West Bengal", "123810");
		Address address2 = new Address("DEF Street", "Opp. abc", "Kolkata", "West Bengal", "123899");
		Guide guide = new Guide("SF13942", "Ramesh Kumar", 100000);	
		List<String> extracurricular_Activity = new ArrayList<String>();
		extracurricular_Activity.add("Footbal");
		extracurricular_Activity.add("Drawing");
		extracurricular_Activity.add("Singing");
		Student student = new Student(5l, "Bhaskar", "koley", address1, address2, guide, StudentStatus.ACTIVE, extracurricular_Activity);		
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
