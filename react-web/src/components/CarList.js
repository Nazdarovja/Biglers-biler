import React from 'react';
import Car from './Car';

export default class CarList extends React.Component {

    render() {
        let list
        let noMatch

        list = this.props.cars.map((car, index) => {
            return <Car fromDate={this.props.fromDate} toDate={this.props.toDate} key={index} car={car} />
        });
        noMatch = () => {
            if (list.length === 0) return <p>Ingen resultater</p>
        }



        return (
            <div>
                {list}
                {noMatch()}
            </div>
        );
    }

}

















