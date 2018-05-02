import React from 'react';
import Car from './Car';

export default class CarList extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const list = this.props.cars.map((car) => {
            return <Car car={car} />
        });

        return (
            <div>
                {list}
            </div>
        );
    }

}

















