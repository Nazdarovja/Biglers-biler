import React, { Component } from 'react';
import '../styles/App.css';

const locations = [
    'Cph (Copenhagen Airport)', 
    'Billund Lufthavn', 
    'Aalborg Lufthavn',
    'Copenhagen City',
    'Aarhus City',
    'Odense',
    'Herning',
    'Roskilde',
    'Esbjerg',
    'Naestved'
];

export default class SideSearch extends Component {
    constructor(props){
        super(props)

        this.state = {
            // location: '',
            // fromdate: new Date().toISOString().substr(0, 10),
            // todate: new Date().toISOString().substr(0, 10),
            error: undefined
        }
    }

//   async componentDidMount(){
//         let location = ''
//         let fromdate = ''
//         let todate = ''

//         if(this.props.location) location = this.props.location
//         if(this.props.fromdate) fromdate = this.props.fromdate
//         if(this.props.todate) todate = this.props.todate

//         await this.setState({location: location, fromdate: fromdate, todate: todate});
//         if(this.state.fromdate.length > 0 && this.state.todate.length > 0)
//             await this.props.fetchCars(this.state.fromdate, this.state.todate, this.state.location);
//     }

    handleChange = (event) => {
        this.props.liftState({[event.target.id]: event.target.value});
    }

    handleSubmit = (event) => {
        try{
            event.preventDefault();
            if (this.props.fromdate.length === 0 || this.props.todate.length === 0) {
                throw {message: 'A date must be selected for both date fields.'}
            }
            const seaFromDate = new Date(this.props.fromdate);
            const seaToDate = new Date(this.props.todate);
            if (seaFromDate > seaToDate) throw {message: 'The beginning date must be a date which is before the end date'}

            this.props.fetchCars();
            this.setState({error: undefined})
        } catch (ex) {
            this.setState({error: ex})
        }   
    }

    createSelect = (title, list, selected) => {
        const options = list.map((item, index) => <option key={index} value={item}>{item}</option>);
        return (
            <div>
            <label className='label-search' htmlFor={title}>Rental location</label>
                <select value={selected} name={title} id={title} onChange={this.handleChange}>
                    <option value=''>Not chosen</option>
                    {options}
                </select>
            </div>
        )
    }

    error = () => {
        if(this.state.error) {
            return (
                <div className='error-container'>
                    {this.state.error.message}
                </div>
            )
        }
        
    }

    render() {
        return (
            <div>
                <label className='label-search' htmlFor='fromdate'>Rent car from</label>
                <input type="date" name="fromdate" id="fromdate" min={new Date().toISOString().substr(0, 10)} value={this.props.fromdate} onChange={this.handleChange}/>
                <br/>
                <label className='label-search' htmlFor='todate'>Rent car to</label>
                <input type="date" name="todate" id="todate" min={this.props.fromdate} value={this.props.todate} onChange={this.handleChange}/>
                <br/>
                {this.createSelect('location', locations, this.props.location)}
                <br/>
                {this.error()}
                <button type="submit" onClick={this.handleSubmit}>Find Bigler</button>
            </div>
        )
    }
}