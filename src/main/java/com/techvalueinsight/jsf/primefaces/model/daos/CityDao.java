package com.techvalueinsight.jsf.primefaces.model.daos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.techvalueinsight.jsf.primefaces.model.entities.City;
import com.techvalueinsight.jsf.primefaces.util.config.HibernateUtil;


public class CityDao {

	public City getCityById(long id){
		
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<City> criteriaQuery = builder.createQuery(City.class);
			Root<City> root = criteriaQuery.from(City.class);
			System.out.println("findById City start");
			criteriaQuery.select(root).where(builder.equal(root.get("cityId"), id));
			Query<City> q = session.createQuery(criteriaQuery);
			City City = q.getSingleResult();
			System.out.println("findById City start");
			return City;
		} catch (Exception e) {
			e.printStackTrace();
			 System.out.println(e.getMessage());
			return null;
		}
	}
	
public City getCityByValue(String value) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			 System.out.println("findByValue City start");
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<City> criteriaQuery = builder.createQuery(City.class);
			Root<City> root = criteriaQuery.from(City.class);
			
			criteriaQuery.select(root).where(builder.equal(root.get("cityValue"), value));
			Query<City> q = session.createQuery(criteriaQuery);
			City City = q.getSingleResult();
			
			 System.out.println("findByValue City end");
			return City;
		} catch (Exception e) {
			 System.err.println(e);
			return null;
		}
	}

	public List<City> loadCities() {
		Transaction transaction = null;
		List<City> Cities = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<City> query = builder.createQuery(City.class);
			Root<City> root = query.from(City.class);

			// Here returns all resultSet from the DB as there's no condition
			query.select(root);

			Query<City> q = session.createQuery(query);
			Cities = q.getResultList();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			 System.err.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		}

		return Cities;
	}
}


