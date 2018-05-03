import React from 'react';
import '../styles/Car.css';

export default class Booking extends React.Component {

    constructor(props){
        super(props)

        this.car = props.match.params.car;

        this.book = this.book.bind(this);

        const now = new Date();
        const day = ('0' + now.getDate());
        const month = ('0' + now.getMonth());
        const year =  now.getFullYear();

        this.state = {
            date: year + '-' + month + '-' + day,
        }
    }

    handleChange = (event) => {
        this.setState({[event.target.id]: event.target.value});
    }

    handleSubmit(event) {
        alert('submitted: ' + this.state.value);
        event.preventDefault();
      }

    book(){
        console.log(this.state)
        if(this.state.email === undefined || this.state.datestart === undefined||this.state.dateend === undefined){
            alert("please specify an email, startdate and enddate")
        } else
        this.setState({car: this.props.car});
    }

    render() {
        console.log(JSON.stringify(this.car));
        return (
            <div>
                <p><img className="logo-mini" alt="" src={this.car.picture}></img></p>
                {/* <p><img className="logo-mini" alt="" src={this.props.car.logo}></img></p> */}
                <p>Company: {this.car.company}</p>
                <p>category: {this.car.category}</p>
                <p>make: {this.car.make}</p>
                <p>model: {this.car.model}</p>
                <p>year: {this.car.year}</p>
                <p>regno: {this.car.regno}</p>
                <p>seats: {this.car.seats}</p>
                <p>doors: {this.car.doors}</p>
                <p>gear: {this.car.gear}</p>
                <p>aircondition: {this.car.aircondition}</p>
                <p>location: {this.car.location}</p>
                <p>priceperday: {this.car.priceperday}</p>

                        {this.state.car ?

                        <span>
                        <h2>Car Booked! [NOT FUNCTIONAL YET]</h2>   
                        <p>email: {this.state.email}</p>
                        <p>Start date: {this.state.datestart}</p>
                        <p>End date: {this.state.dateend}</p>
                        </span>
                    
                        : 
           
                <span>
                <h2>Book car</h2>
                <p>Your email: <input type="text" name="email" id="email" onChange={this.handleChange}/></p>
                <p>Start date: <input type="date" name="datestart" id="datestart" value={this.state.date1} onChange={this.handleChange}/></p>
                <p>End date: <input type="date" name="dateend" id="dateend" value={this.state.date2} onChange={this.handleChange}/></p>
                <button type="button" onClick={this.book} className="confirm-booking" >Accept Booking [not yet implemented]</button>
                </span>
            }

            </div>

        );

    }

}

















