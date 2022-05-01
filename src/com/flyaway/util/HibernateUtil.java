package com.flyaway.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.flyaway.entity.*;

public class HibernateUtil {
	static SessionFactory sessionFactory;
	public static SessionFactory buildSessionFactory() {
		if(sessionFactory==null) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Admin.class);
		cfg.addAnnotatedClass(Airline.class);
		cfg.addAnnotatedClass(Flight.class);
		cfg.addAnnotatedClass(Place.class);
		sessionFactory = cfg.buildSessionFactory();
		}
		return sessionFactory;
	}
}
