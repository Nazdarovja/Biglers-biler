/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stanislav
 */
public class CarList {

    List<Car> cars = new ArrayList();

    public CarList(List<Car> cars) {
        this.cars = cars;
    }

}
