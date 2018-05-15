package com.carrental.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Car {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private Long id;
	private String logo;
	private String company;
	private String category;
	private String picture;
	private String make;
	private String model;
	private int year;
	private String regno;
	private int seats;
	private int doors;
	private String gear;
	private boolean aircondition;
	private String location;
	private int priceperday;

	@OneToMany(mappedBy = "car", cascade=CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<Reservation>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public String getGear() {
		return gear;
	}

	public void setGear(String gear) {
		this.gear = gear;
	}

	public boolean isAircondition() {
		return aircondition;
	}

	public void setAircondition(boolean aircondition) {
		this.aircondition = aircondition;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPriceperday() {
		return priceperday;
	}

	public void setPriceperday(int priceperday) {
		this.priceperday = priceperday;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", logo=" + logo + ", company=" + company + ", category=" + category + ", picture="
				+ picture + ", make=" + make + ", model=" + model + ", year=" + year + ", regno=" + regno + ", seats="
				+ seats + ", doors=" + doors + ", gear=" + gear + ", aircondition=" + aircondition + ", location="
				+ location + ", priceperday=" + priceperday + ", reservations=" + reservations + "]";
	}
	
	
}
