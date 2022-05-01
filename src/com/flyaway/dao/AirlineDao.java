package com.flyaway.dao;

import com.flyaway.util.*;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.flyaway.entity.*;

public class AirlineDao {

	public static boolean addAirline(Airline a) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		try {
		Transaction tx = session.beginTransaction();
		// Transaction begin
		session.save(a);
		// Transaction end
		tx.commit();
		return true;}
		catch(RollbackException rbe) {
			return false;
		}
	}
	
	public static Airline getAirline(int airlineId) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		Query q;
		try {
			q = session.createQuery("from Airline where airlineId=:aid");
			q.setParameter("aid", airlineId);
			Airline a = (Airline) q.getSingleResult();
			return a;
		}
		catch(NonUniqueResultException ure) {
			System.out.println("Several results found");
			return null;
		}
		catch(NoResultException nre) {
			System.out.println("No results found!!");
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	public static List<Airline> listAirlines(){
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		List<Airline> airlineList = session.createQuery("from Airline").list();
		return airlineList;
	}
	
	public static int validateAirline(Airline a) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		Query q;
		q = session.createQuery("from Airline where airlineName=:airlineName and scope=:scope");
		q.setParameter("airlineName", a.getAirlineName());
		q.setParameter("scope", a.getScope());
		try {
			q.getSingleResult();
			return 1;
		}
		catch(NonUniqueResultException ure) {
			System.out.println("Several results found");
			return q.getResultList().size();
		}
		catch(NoResultException nre) {
			System.out.println("No results found!!");
			return 0;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public static boolean modifyAirline(Airline a) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		Query q;
		try {
			Transaction tx = session.beginTransaction();
			// Transaction begin
			q = session.createQuery("update Airline set airlineName=:airlineName,scope=:scope where airlineId=:airlineId");
			q.setParameter("airlineName", a.getAirlineName());
			q.setParameter("scope", a.getScope());
			q.setParameter("airlineId", a.getAirlineId());
			int r = q.executeUpdate();
			// Transaction end
			tx.commit();
			System.out.println(r);
			return r==1;}
			catch(RollbackException rbe) {
				return false;
			}
	}
	
	public static boolean deleteAirline(Airline a) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session s = sf.openSession();
		
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery("delete from Airline where airlineId=:airlineId");
		q.setParameter("airlineId",a.getAirlineId());
		int r = q.executeUpdate();
		tx.commit();
		System.out.println("r = "+r + "status="+(r==1));
		return r==1;
	}
}
