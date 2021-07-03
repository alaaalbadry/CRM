package com.techvalueinsight.jsf.primefaces.model.entities;

import java.io.InputStream;
import java.io.Serializable;

//import org.hibernate.annotations.Entity;
//import org.hibernate.annotations.NamedQuery;
//import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, precision=22)
	private long id;
	
	@Column
	private String customerName;
	
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private Country country;
	
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private City city;
	
	@Column
	private String address;
	@Column
	private String taxNumber;
	@Column
	private boolean active;
	@Column
	private String customerPicPath;
	
	@Transient
	private InputStream fileInputStream;

	public Customer() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCustomerPicPath() {
		return customerPicPath;
	}

	public void setCustomerPicPath(String customerPicPath) {
		this.customerPicPath = customerPicPath;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	
	

}
