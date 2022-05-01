package com.flyaway.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.flyaway.entity.Admin;
import com.flyaway.util.HibernateUtil;

public class AdminDao {

	public static boolean addAdmin(Admin a) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();

		try {
			Transaction tx = session.beginTransaction();
			// Transaction begin
			session.save(a);
			// Transaction end
			tx.commit();
			return true;
		} catch (RollbackException rbe) {
			return false;
		}
	}

	public static List<Admin> listAdmins() {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		List<Admin> adminList = session.createQuery("from Admin").list();
		return adminList;

	}

	public static boolean modifyAdmin(Admin aold, Admin anew) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("update Admin set password=:password where adminId=:id");
		q.setParameter("password", anew.getPassword());
		q.setParameter("id", aold.getAdminId());
		int status = q.executeUpdate();
		tx.commit();
		return (status==1);
	}

	public static Admin getAdmin(String userName, String password) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		try {
			Query q = session.createQuery("from Admin where userName=:uname and password=:pwd");
			q.setParameter("uname", userName);
			q.setParameter("pwd", password);
			//int r = q.getMaxResults();
			Admin ar = (Admin) q.getSingleResult();
			/*if (r == 1) {
				ar = (Admin) q.getSingleResult();
			} else if (r == 0) {
				throw new NoResultException();
			} else if (r < 0) {
				throw new Exception("Updated rows not valid. r =" + r);
			} else {
				throw new NonUniqueResultException();
			}*/
			return ar;
		} catch (NonUniqueResultException e) {
			System.out.println("More than One records found!! Kindly validate..");
			return null;
		} catch (NoResultException nre) {
			System.out.println("No results found! Kindly check if the admin is registered!");
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static boolean validateAdmin(Admin a) {
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		try {
			Query q = session.createQuery("from Admin where userName=:uname and password=:pwd");
			q.setParameter("uname", a.getUserName());
			q.setParameter("pwd", a.getPassword());
			Admin ar =(Admin) q.getSingleResult();
			return true;
		} catch (NonUniqueResultException e) {
			System.out.println("More than One records found!! Kindly validate..");
			return false;
		} catch (NoResultException nre) {
			System.out.println("No results found! Kindly check if the admin is registered!");
			return false;
		}
	}
}
