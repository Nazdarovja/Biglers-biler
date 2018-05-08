import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import StartSearch from './StartSearch';
import facade from '../Facade';
import '../styles/Car.css';

export default class Car extends Component {
    constructor(props) {
        super(props);

        this.state = {
            location: '',
            fromdate: '',
            todate: '',
            error: undefined
        }
        this.findCars = this.findCars.bind(this);
    }

    findCars(cb) {
        facade.fetchData()
          .then((res) => {
            let cars
            (cb) ? cars = cb(res) : cars = res;
            this.setState({ cars: cars, error: undefined, filteredCars: cars })
          }).catch((ex) => this.setState({ error: ex.message + ', ' + ex.status }))
      }

    handleChange = (event) => {
        this.setState({ [event.target.id]: event.target.value });
    }

    render() {

        return (
            <div>
                <h1>Biglers Biler</h1>
                <div className="Car">
                    <img alt="" src='https://i.imgur.com/t0qqq7l.png'></img>

                    <StartSearch />
                </div>
            </div>
        )
    }
}



