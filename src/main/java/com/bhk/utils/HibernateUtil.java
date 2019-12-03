package com.bhk.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.bhk.entity.Address;
import com.bhk.entity.Student;

public class HibernateUtil {

	public static HibernateUtil hibernateUtil;

	private HibernateUtil() {

	}
	
	public static HibernateUtil getInstance() {
		if(hibernateUtil==null) {
			hibernateUtil = new HibernateUtil();
		}
		return hibernateUtil;
	}

	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = null;
		// if (sessionFactory == null) {
		try {
			Configuration configuration = new Configuration();
			// Hibernate settings equivalent to hibernate.cfg.xml's properties
			Properties settings = new Properties();
			settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_db?useSSL=false");
			settings.put(Environment.USER, "root");
			settings.put(Environment.PASS, "Raven1234");
			settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
			settings.put(Environment.SHOW_SQL, "true");
			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			settings.put(Environment.HBM2DDL_AUTO, "create-drop");
			configuration.setProperties(settings);
			configuration.addAnnotatedClass(Student.class);
			configuration.addAnnotatedClass(Address.class);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
		return sessionFactory;
	}
}
