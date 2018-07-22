import React from 'react';
import '../styles/Car.css';
import facade from '../Facade';

export default class Booking extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            car: {},
            succes: false,
            error: undefined,
            fromDate: props.location.state.fromDate,
            toDate: props.location.state.toDate,
            email: ""
        }
    }

    async componentDidMount() {
        const obj = await facade.fetchSpecCar(this.props.match.params.regno);
        //DEBUG
        console.log(obj);
        await this.setState({ car: obj.cars[0] });
    }

    error = () => {
        if (this.state.error)
            return <p className='error-booking'>{this.state.error}</p>
        else
            return null;
    }

    handleChange = (event) => {
        this.setState({ [event.target.id]: event.target.value });
    }

    successfullBooking = () => {

        return (
            <div>
                <h2>Car is Booked!</h2>
                <p>Email: {this.state.email}</p>
                <p>From date: {this.state.toDate}</p>
                <p>End date: {this.state.fromDate}</p>
            </div>)
    }


    handleSubmit = async (event) => {
        event.preventDefault();

        if (this.state.email === undefined) {
            this.setState({ error: "Please specify an email for booking" });
        } else if (this.state.fromDate === undefined || this.state.toDate === undefined) {
            this.setState({ error: "Please specify from/to -date for booking" });
        } else {
            //add reservation to car object
            const car = this.addReservation();
            //PUT the obj to DB
            await facade.putCarReservation(car);
            const isSuccess = await this.bookingSuccessCheck();
            this.setState({ succes: isSuccess });
        }


    }

    addReservation = () => {
        const car = this.state.car;
        //Create the reservation to be added to the car obj.
        const res = {
            companyTag: car.company,
            customerMail: this.state.email,
            fromDate: this.convDate(this.state.fromDate),
            toDate: this.convDate(this.state.toDate),
        }
        //clear previous reservations
        car.reservations = [];
        //add new reservation for PUT method
        car.reservations.push(res);
        return car;
    }

    bookingSuccessCheck = async () => {

        //TODO : fix success criteria stuff (temp)

        
        // let dbCar;

        // let res = await facade.fetchSpecCar(this.state.car.regno)
        // dbCar = res.cars[0];

        // // .then((res) => {
        // //         dbCar = res.cars[0];
        // //     }).catch((ex) => this.setState({ error: "Something went wrong, sorry" }))

        //     //get list of reservations from fetched car
        // let resList = dbCar.reservations;

        // //Converts to same date format
        // let fromDate = this.convDate(this.state.fromDate)
        // let toDate = this.convDate(this.state.toDate)

        // //check if it contains the reservation 
        // const list = resList.filter((res) => {
        //     if (res.fromDate === fromDate && res.toDate === toDate) {
        //         return res;
        //     }
        // })
        // //returns true if res is contained, else false
        // if (list.length < 1)
        //     return false
        // else
        return true
    }



    convDate = (date) => {
        let res;
        res = date.substring(8) + '/' + date.substring(5, 7) + '/' + date.substring(0, 4)
        return res;
    }

    bookingForm = () => {
        return (<div >
            <h2>Book car</h2>
            <p>Your email: <input type="email" name="email" id="email" placeholder="your@email.pls" onChange={this.handleChange} value={this.state.email} /></p>
            <p>From date: <input type="date" name="datestart" id="fromDate" min={new Date().toISOString().substr(0, 10)} value={this.state.fromDate} onChange={this.handleChange} /></p>
            <p>End date: <input type="date" name="dateend" id="toDate" min={this.state.fromDate} value={this.state.toDate} onChange={this.handleChange} /></p>
            {this.error()}
            <button onClick={this.handleSubmit}>Submit Booking</button>
        </div >
        )
    }

    render() {
        return (
            <div className='grid-container-start'>
                <div className='booking-container'>
                    <CarDisplay car={this.state.car} />
                    {this.state.succes ?
                        this.successfullBooking()
                        :
                        this.bookingForm()
                    }
                </div>
            </div>

        );

    }

}

const CarDisplay = (props) => {
    return (
        <div className='booking-carinfo-container'>
            <div className='booking-carinfo-item'>
                <p><img className="logo-mini" alt="" src={props.car.picture}></img></p>

            </div>
            <div className='booking-carinfo-item'>
                <p>Company:
                <br />
                    {props.car.company}</p>
                <p>Category: {props.car.category}</p>
                <p>Make: {props.car.make}</p>
                <p>Model: {props.car.model}</p>
                <p>Year: {props.car.year}</p>
                <p>Reg. No.: {props.car.regno}</p>
            </div>
            <div className='booking-carinfo-item'>
                <p>Seats: {props.car.seats}</p>
                <p>Doors: {props.car.doors}</p>
                <p>Gear: {props.car.gear}</p>
                <p>Aircondition: {props.car.aircondition ? "Yes" : "No"}</p>
                <p>Location: {props.car.location}</p>
                <p>Price per day: {props.car.priceperday}</p>
            </div>
        </div>
    )
}