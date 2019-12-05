package com.bhk.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bhk.entity.Guide;
import com.bhk.entity.Student;
import com.bhk.utils.HibernateUtil;

public class GuideDAO {
	private HibernateUtil hibernateUtil;
	private Transaction transaction = null;
	

	public GuideDAO() {
		hibernateUtil = HibernateUtil.getInstance();
	}

	public boolean save(Guide guide) {
		try (Session session = hibernateUtil.getSessionFactory().getCurrentSession()) {
			transaction = session.beginTransaction();
			// save the guide object			
			session.save(guide);
			// commit transaction
			transaction.commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public List<Guide> getGuide() {
		try (Session session = hibernateUtil.getSessionFactory().getCurrentSession()) {
			return session.createQuery("from Guide", Guide.class).list();
		}
	}


}
