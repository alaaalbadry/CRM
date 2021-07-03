package com.techvalueinsight.jsf.primefaces.model.daos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.techvalueinsight.jsf.primefaces.model.entities.Country;
import com.techvalueinsight.jsf.primefaces.util.config.HibernateUtil;

public class CountryDao {

	public Country getCountryById(long id){
		
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Country> criteriaQuery = builder.createQuery(Country.class);
			Root<Country> root = criteriaQuery.from(Country.class);
			System.out.println("findById Country start");
			criteriaQuery.select(root).where(builder.equal(root.get("countryId"), id));
			Query<Country> q = session.createQuery(criteriaQuery);
			Country Country = q.getSingleResult();
			System.out.println("findById Country start");
			return Country;
		} catch (Exception e) {
			e.printStackTrace();
			 System.out.println(e.getMessage());
			return null;
		}
	}
	
public Country getCountryByValue(String value) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			 System.out.println("findByValue Country start");
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Country> criteriaQuery = builder.createQuery(Country.class);
			Root<Country> root = criteriaQuery.from(Country.class);
			
			criteriaQuery.select(root).where(builder.equal(root.get("countryValue"), value));
			Query<Country> q = session.createQuery(criteriaQuery);
			Country Country = q.getSingleResult();
			
			 System.out.println("findByValue Country end");
			return Country;
		} catch (Exception e) {
			 System.err.println(e);
			return null;
		}
	}

	public List<Country> loadCountries() {
		Transaction transaction = null;
		List<Country> Countries = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Country> query = builder.createQuery(Country.class);
			Root<Country> root = query.from(Country.class);

			// Here returns all resultSet from the DB as there's no condition
			query.select(root);

			Query<Country> q = session.createQuery(query);
			Countries = q.getResultList();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			 System.err.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		}

		return Countries;
	}
}


