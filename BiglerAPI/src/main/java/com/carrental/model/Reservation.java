package com.carrental.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.carrental.helper.*;
import javax.persistence.FetchType;


@Entity
public class Reservation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private Long id;
	
	@JsonIgnore
	@JoinColumn
	@ManyToOne
	private Car car;
	private String companyTag;
	private String customerMail;
	
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date fromDate;
	
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date toDate;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public String getCompanyTag() {
		return companyTag;
	}
	public void setCompanyTag(String companyTag) {
		this.companyTag = companyTag;
	}
	public String getCustomerMail() {
		return customerMail;
	}
	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFromDate() {
		return fromDate;
	}
	
	@JsonDeserialize(using = CustomDateDeserializer.class, as = Date.class)
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	public Date getToDate() {
		return toDate;
	}
	
	@JsonDeserialize(using = CustomDateDeserializer.class, as = Date.class)
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	@Override
	public String toString() {
		return "Reservation [companyTag=" + companyTag + ", customerMail=" + customerMail + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}

	
}
