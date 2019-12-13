package com.bhk.app;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.bhk.entity.Address;
import com.bhk.entity.Guide;
import com.bhk.entity.Student;
import com.bhk.entity.Subject;
import com.bhk.utils.HibernateUtil;
import com.bhk.utils.StudentStatus;

public class Application {

	public static void main(String[] args) throws Exception{
		Address address1 = new Address("ABC Street", "Opp. xyz", "Kolkata", "West Bengal", "123810");
		Address address2 = new Address("DEF Street", "Opp. abc", "Kolkata", "West Bengal", "123899");
		Subject subject1 = new Subject("English", "Amit G");
		Subject subject2 = new Subject("Hindi", "Sumit G");
		Guide guide = new Guide("SF13942", "Ramesh Kumar", 100000);	
		List<String> extracurricular_Activity = new ArrayList<String>();
		extracurricular_Activity.add("Footbal");
		extracurricular_Activity.add("Drawing");
		extracurricular_Activity.add("Singing");
		Student student = new Student(5l, "Bhaskar", "koley", address1, address2, guide, subject1, StudentStatus.ACTIVE, extracurricular_Activity);	
		student.setSubjects(subject2);
		HibernateUtil hibernateUtil = HibernateUtil.getInstance();
		Transaction transaction = null;
		/*
		 * For Hibernate specific connection to Database we need to use Session and SessionFactory classes
		 */
		try (Session session = hibernateUtil.getSessionFactory().getCurrentSession()) {
			transaction = session.beginTransaction();
			
			// Use the save function to create the rows in the DB. 
			// But you need to call the save function for all the objects, you want to save.
			//session.save(guide);
			//session.save(student);
			
			// You can use the persist method to save the the related objects by applying CascadeType.PERSIST option.
			session.persist(student);
			Criteria criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.ilike("firstName", "%Ka%"));
			List<Student> studentList = criteria.list();
			if(studentList != null && studentList.size() > 0) {
				System.out.println(studentList.get(0).toString());
			}	
			
			transaction.commit();
		}catch (Exception ex) {
			ex.printStackTrace();			
		}
		
		/*
		 * For Generic Connection Irrespective to the JPA provider we need to use EntityManager and EntityManagerFactory classes. 
		 */
		/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("My-Connection");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction txn = entityManager.getTransaction();
		try {		
			
			txn.begin();
			
			//entityManager.persist(student);
			
			Dog dog = new Dog();
			dog.setName("Bruno");
			
			Cat cat = new Cat();
			cat.setName("Caty");
			
			entityManager.persist(dog);
			entityManager.persist(cat);
			
			List<Student> studentList = entityManager.createNamedQuery("findByName").setParameter("firstName", "Bhaskar").getResultList();
			if(studentList != null && studentList.size() > 0) {
				System.out.println(studentList.get(0).toString());
			}
			
			Query query = entityManager.createNamedQuery("findGuideByName");
			query.setParameter("firstName", "Bhaskar");			
			List<Object[]> studentGuideData = query.getResultList();
			if(studentGuideData != null && studentGuideData.size() > 0) {
				System.out.println(studentGuideData.get(0)[0].toString()+ " "+studentGuideData.get(0)[1].toString()+ " "+studentGuideData.get(0)[2].toString());
			}	
			
			
			txn.commit();
			
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}finally {
			if(entityManager != null) entityManager.close();
		}*/

	}

}
