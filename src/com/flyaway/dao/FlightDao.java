package com.flyaway.dao;

import org.hibernate.Transaction;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.flyaway.entity.Flight;
import com.flyaway.util.HibernateUtil;

public class FlightDao {
	public static boolean addFlight(Flight f) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session s = sf.openSession();
		try {
		Transaction tx = s.beginTransaction();
		//
		s.save(f);
		//
		tx.commit();
		return true;
		}
		catch(RollbackException rbe) {
			return false;
		}
	}
	
	public static List<Flight> listFlights() {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		List<Flight> flights = session.createQuery("from Flight").list();
		return flights;
	}
	
	public static int validateFlight(Flight f) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session s = sf.openSession();
		Query q = s.createQuery("from Flight where source=:source and destination=:destination and airline=:airline and ticketPrice=:ticketPrice");
		q.setParameter("source", f.getSource());
		q.setParameter("destination", f.getDestination());
		q.setParameter("airline", f.getAirline());
		q.setParameter("ticketPrice",f.getTicketPrice());
		try {
			if(f.getSource().equals(f.getDestination())) {
				throw new Exception("The source and destination are provided with same place!! Please check the flight details validity to add it.");
			}
		int r =q.getResultList().size();
		return r;
		}
		catch(NoResultException nre) {
			System.out.println("No Results found!");
			return 0;
		}
		catch(NonUniqueResultException ure) {
			System.out.println("Several results found!");
			return q.getResultList().size();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return -1;
		}
	}
	
	public static boolean deleteFlight(Flight f) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session s = sf.openSession();
		Query q = s.createQuery("delete from Flight where flightId=:flightId");
		q.setParameter("flightId", f.getFlightId());
		Transaction tx = s.beginTransaction();
		int r = q.executeUpdate();
		tx.commit();
		System.out.println("r = "+r + "status="+(r==1));
		return r==1;
	}
	
	public static int modifyFlight(int seats,Flight f) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session s = sf.openSession();
		Query q = s.createQuery("from Flight where flightId=:flightId");
		q.setParameter("flightId", f.getFlightId());
		int available_seats = f.getSeats();
		try {
		Flight f1 = (Flight) q.getSingleResult();
		
		f1.setSeats(f1.getSeats() - seats);
		Transaction tx = s.beginTransaction();
		//
		s.save(f1);
		//
		tx.commit();
		
		Flight fr = (Flight) q.getSingleResult();
		return available_seats - fr.getSeats();
		}
		catch(NoResultException nre) {
			System.out.println("No Results found!");
			return 0;
		}
		catch(NonUniqueResultException ure) {
			System.out.println("Several results found!");
			return q.getResultList().size();
		}
		catch(RollbackException rbe) {
			System.out.println(rbe.getMessage());
			return -1;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return -1;
		}
	}
	
	public static Flight getFlight(String flightId) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session s = sf.openSession();
		try {
		Query q = s.createQuery("from Flight where flightId=:flightId");
		q.setParameter("flightId", flightId);
		Flight fr = (Flight) q.getSingleResult();
		return fr;
		}
		catch(NoResultException nre) {
			System.out.println("No Results found!");
			System.out.println(flightId);
			return null;
		}
		catch(NonUniqueResultException ure) {
			System.out.println("Several results found!");
			return null;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
}
