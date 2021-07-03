package com.techvalueinsight.jsf.primefaces.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY")
@NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COUNTRY_ID", unique = true, nullable = false, precision = 22)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long countryId;

	@Column
	private String countryValue;

	public Country() {
	}

	
	
	public long getCountryId() {
		return countryId;
	}



	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}



	public String getCountryValue() {
		return countryValue;
	}



	public void setCountryValue(String countryValue) {
		this.countryValue = countryValue;
	}



	@Override
	public boolean equals(Object other) {
		return (other instanceof Country) && (countryId > 0) ? countryId == (((Country) other).countryId) : (other == this);
	}
}


