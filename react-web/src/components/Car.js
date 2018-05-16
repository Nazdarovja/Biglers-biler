import React, { Component } from 'react';
import '../styles/Car.css';
import CarInfo from './CarInfo';
import { Link } from 'react-router-dom'

export default class Car extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showComponent: false, car: undefined
    }
  }

  _onButtonClick = (event) => {
    event.preventDefault();
    if (this.state.showComponent === true) {
      this.setState({
        showComponent: false,
      });
    } else {
      this.setState({
        showComponent: true,
      });
    }
  }


  render() {
    const fromDate = this.props.fromDate;
    const toDate = this.props.toDate;
    return (
      <div className="Car">
        <h3 className="Car-header">{this.props.car.category}</h3>
        <img className="Car-picture" alt="" src={this.props.car.picture}></img>
        <p className="Car-link">
          <a onClick={this._onButtonClick} href="">Details</a>
        </p>
        {this.state.showComponent ?
          <CarInfo car={this.props.car} /> : null
        }
        <p className="Car-price">Price per day: {this.props.car.priceperday}</p>
        <Link
          to={{
            pathname :`/Booking/${this.props.car.regno}`,
            state: { fromDate: fromDate, toDate: toDate }
          }}
        >
          Order Car
          </Link>
          {/* TODO: DEBUG (remove p tag below) */}
          <p>{fromDate} {toDate}</p>
      </div>
    )
  }
}