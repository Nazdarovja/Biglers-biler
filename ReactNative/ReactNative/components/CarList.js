import React from 'react';
import { View } from 'react-native';
import Car from './Car';

export default class CarList extends React.Component {

    render() {
        const list = this.props.cars.map((car, index) => {
            return <Car key={index} car={car} />
        });

        return (
            <View style={{width: '100%'}}>
                {list}
            </View>
        );
    }
}