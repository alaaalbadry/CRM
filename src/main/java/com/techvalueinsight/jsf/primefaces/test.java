package com.techvalueinsight.jsf.primefaces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.techvalueinsight.jsf.primefaces.util.config.HibernateUtil;

public class test {

	public static void main (String []  args) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		SessionFactory sf = new Configuration()
//			    .configure("Hibernate.cfg.xml")
//			    .buildSessionFactory();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("Hibernate.cfg.xml")
				.build();

		// Create a metadata sources using the specified service registry.
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

		SessionFactory sf= metadata.getSessionFactoryBuilder().build();
		sf.openSession();
	}
	
}
