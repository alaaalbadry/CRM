package com.techvalueinsight.jsf.primefaces.model.daos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.techvalueinsight.jsf.primefaces.model.entities.Customer;
import com.techvalueinsight.jsf.primefaces.util.config.HibernateUtil;


public class CustomerDao {
	
	private static Session session;
	
	public CustomerDao() {
		session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("CustomerrDao  constructor has been created");
	}
	

	public boolean save(Customer Customer) {
		System.out.println("Adding Customer start.");
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			session.save(Customer);
			transaction.commit();
			System.out.println("Adding Customer successfully, id: " + Customer.getId());
		} catch (Exception e) {
			System.err.println("Adding Customer FAILED, " + e);

			if (transaction != null) {
				transaction.rollback();
			}
			return false;
		}
		return true;
	}

	public boolean update(Customer Customer) {
		System.out.println("updating Customer start.");
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			session.update(Customer);
			transaction.commit();
			System.out.println("updating Customer successfully, id: " + Customer.getId());
		} catch (Exception e) {
			System.err.println("updating Customer FAILED, " + e);
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			return false;
		}
		return true;
	}

	public boolean delete(long id) {
		try {
			Transaction transaction = session.beginTransaction();
			Customer customer = session.find(Customer.class, id);

			if (customer != null)
				session.delete(customer);
			transaction.commit();
			System.out.println(" the Customer deleted successfully");
			return true;
		} catch (Exception e) {
			System.err.println("delete Customer FAILED, " + e);
			e.printStackTrace();
			return false;
		}
	}
	public List<Customer> findAll() {

		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			System.out.println("findAll Customers start");
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
			Root<Customer> root = criteriaQuery.from(Customer.class);

//			criteriaQuery.select(root).where(findAllLogic(builder, criteriaQuery, root));

			Query<Customer> q = session.createQuery(criteriaQuery);
			List<Customer> Customers = q.getResultList();
			System.out.println("findAll Contracts end");
			return Customers;
		} catch (Exception e) {
			System.err.println("findall method error: " + e);
			return null;
		}
	}

	public Customer findById(long id) {

		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			System.out.println("findById Customer start");
			CriteriaBuilder builder = session.getCriteriaBuilder();
			Customer customer = session.find(Customer.class, id);
			if(customer!=null) {
				System.out.println("findById Customer end");
				return customer;
			}else {
				System.out.println("customer not found");
			}
			
		} catch (Exception e) {
			System.err.println("findbyid method error: " + e);
			System.err.println(e);
			return null;
		}
		return null;

	}


}
