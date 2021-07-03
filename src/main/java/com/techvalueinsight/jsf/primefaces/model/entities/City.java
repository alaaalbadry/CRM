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
@Table(name = "CITY")
@NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CITY_ID", unique = true, nullable = false, precision = 22)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long cityId;

	@Column
	private String cityValue;

	public City() {
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getCityValue() {
		return cityValue;
	}

	public void setCityValue(String cityValue) {
		this.cityValue = cityValue;
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof City) && (cityId > 0) ? cityId == (((City) other).cityId) : (other == this);
	}
}

