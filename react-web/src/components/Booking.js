import React from 'react';
import '../styles/Car.css';
import facade from '../Facade';

export default class Booking extends React.Component {

    constructor(props) {
        super(props);
        this.fromDate = props.location.state.fromDate;
        this.toDate = props.location.state.toDate;
        this.state = {
            car: {},
            succes: false
        }
    }

    handleChange = (event) => {
        this.setState({ [event.target.id]: event.target.value });
    }

    handleSubmit(event) {
        event.preventDefault();
    }

    book() {
        if (this.state.email === undefined || this.state.datestart === undefined || this.state.dateend === undefined) {
            // ("Please specify an email, startdate and enddate")
        } else
            this.setState({ succes: true });
    }

    async componentDidMount() {
        const obj = await facade.fetchSpecCar(this.props.match.params.regno);
        this.setState({ car: obj.cars[0] });
    }


    render() {
        return (
            <div className='grid-container-start'>
                <div className='booking-container'>
                    <div className='booking-carinfo-container'>
                        <div className='booking-carinfo-item'>
                            <p><img className="logo-mini" alt="" src={this.state.car.picture}></img></p>

                        </div>
                        <div className='booking-carinfo-item'>
                            <p>Company:<br/>
                            {this.state.car.company}</p>
                            <p>Category: {this.state.car.category}</p>
                            <p>Make: {this.state.car.make}</p>
                            <p>Model: {this.state.car.model}</p>
                            <p>Year: {this.state.car.year}</p>
                            <p>Reg. No.: {this.state.car.regno}</p>
                        </div>
                        <div className='booking-carinfo-item'>
                            <p>Seats: {this.state.car.seats}</p>
                            <p>Doors: {this.state.car.doors}</p>
                            <p>Gear: {this.state.car.gear}</p>
                            <p>Aircondition: {this.state.car.aircondition ? "Yes" : "No"}</p>
                            <p>Location: {this.state.car.location}</p>
                            <p>Price per day: {this.state.car.priceperday}</p>
                        </div>
                    </div>

                    {this.state.succes ?
                        <div>
                            <h2>Car Booked! [NOT FUNCTIONAL YET]</h2>
                            <p>email: {this.state.email}</p>
                            <p>Start date: {this.state.datestart}</p>
                            <p>End date: {this.state.dateend}</p>
                        </div>
                        :
                        <div>
                            <h2>Book car</h2>
                            <p>Your email: <input type="text" name="email" id="email" onChange={this.handleChange} /></p>
                            <p>Start date: <input type="date" name="datestart" id="datestart" min={new Date().toISOString().substr(0, 10)} value={this.fromDate} onChange={this.handleChange} /></p>
                            <p>End date: <input type="date" name="dateend" id="dateend" min={this.fromDate} value={this.toDate} onChange={this.handleChange} /></p>
                            <button onClick={this.book}>Accept Booking [not yet implemented]</button>
                        </div>
                    }
                </div>
            </div>

        );

    }

}

















