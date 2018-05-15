package com.carrental.model;

import java.util.ArrayList;
import java.util.List;

public class Cars {

    private List<Car> cars = new ArrayList<Car>();

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<Car> getCars() {
		return cars;
	}

    @Override
    public String toString() {
        return "Cars{" + "cars=" + cars + '}';
    }
	
}