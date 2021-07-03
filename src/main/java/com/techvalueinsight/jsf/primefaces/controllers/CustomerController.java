package com.techvalueinsight.jsf.primefaces.controllers;


import java.util.List;

import com.techvalueinsight.jsf.primefaces.model.daos.CityDao;
import com.techvalueinsight.jsf.primefaces.model.daos.CountryDao;
import com.techvalueinsight.jsf.primefaces.model.daos.CustomerDao;
import com.techvalueinsight.jsf.primefaces.model.entities.City;
import com.techvalueinsight.jsf.primefaces.model.entities.Country;
import com.techvalueinsight.jsf.primefaces.model.entities.Customer;

public class CustomerController {

	private static CustomerDao customerDao;
	private static CountryDao countryDao;
	private static CityDao cityDao;
	
	public CustomerController() {
		customerDao = new CustomerDao();
		countryDao = new CountryDao();
		cityDao = new CityDao();
	}

	public boolean NewItem(Customer t) {
		return customerDao.save(t);
	}


	public boolean EditItem(Customer t) {
		return customerDao.update(t);
	}

	
	public List<Customer> SearchAllItems() {
		return customerDao.findAll();
	}

	public Customer SearchItem(long id) {
		return customerDao.findById(id);
	}
	
	public boolean deleteItem(Customer t) {
		return customerDao.delete(t.getId());
	}
	
	public List<Country> loadCountries() {
		return countryDao.loadCountries();
	}

	public Country getCountryById(long id) {
		return countryDao.getCountryById(id);
	}

	public List<City> loadCities() {
		return cityDao.loadCities();
	}

	public City getCityById(long id) {
		return cityDao.getCityById(id);
	}

}
