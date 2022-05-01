package com.flyaway.dao;

import org.hibernate.Transaction;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.flyaway.entity.Place;
import com.flyaway.util.HibernateUtil;

public class PlaceDao {
	public static boolean addPlace(Place p) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		try {
		Transaction tx = session.beginTransaction();
		//
		session.save(p);
		//
		tx.commit();
	return true;}
		catch(RollbackException rbe) {
			return false;
		}
		}
	
	public static List<Place> listPlaces(){
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		List<Place> places = session.createQuery("from Place").list();
		return places;
	}
	
	public static int validatePlace(Place p) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		Query q;
		q = session.createQuery("from Place where city=:city and state=:state");
		q.setParameter("city", p.getCity());
		q.setParameter("state", p.getState());
		try {
		q.getSingleResult();
		return 1;
		}
		catch(NoResultException nre) {
			System.out.println("No results found!");
			return 0;
		}
		catch(NonUniqueResultException ure) {
			System.out.println("Several results found.");
			return q.getResultList().size();
		}
		catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static boolean modifyPlace(Place p) {
		int status = validatePlace(p);
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		Query q;
		q = session.createQuery("update Place set city:=city and state:=state where placeId=:placeId");
		q.setParameter("placeId", p.getPlaceId());
		q.setParameter("city", p.getCity());
		q.setParameter("state", p.getState());
		int r=0;
		try {
			if(status==1) {
				r = q.executeUpdate();
				return r==1;
			}
			else {
				return r==1;
			}
		}
		catch(NoResultException nre) {
			System.out.println("No Results found! Check if place is added");
			return false;
		}
		catch(NonUniqueResultException ure) {
			System.out.println("Several results found!! Please check the specific place details to be modified.");
			return false;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static Place getPlace(int placeId) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Place where placeId=:placeId");
		q.setParameter("placeId", placeId);
		try {
		Place p = (Place)q.getSingleResult();
		return p;
		}
		catch(NoResultException nre) {
			System.out.println("No Results found! Check if place is added");
			return null;
		}
		catch(NonUniqueResultException ure) {
			System.out.println("Several results found!! Please check the specific place details to be modified.");
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
