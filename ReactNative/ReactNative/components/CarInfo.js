import React, { Component } from 'react';
import { View, Text, Image} from 'react-native';

export default class componentName extends Component {
    render() {
        return (
            <View className="car-data">
                <Image style={{height:100, width:100}} source={{ uri :this.props.car.logo}} />
                <Text style={{color: 'white'}}>company: {this.props.car.company}</Text>
                <Text style={{color: 'white'}}>make: {this.props.car.make}</Text>
                <Text style={{color: 'white'}}>model: {this.props.car.model}</Text>
                <Text style={{color: 'white'}}>year: {this.props.car.year}</Text>
                <Text style={{color: 'white'}}>regno: {this.props.car.regno}</Text>
                <Text style={{color: 'white'}}>seats: {this.props.car.seats}</Text>
                <Text style={{color: 'white'}}>doors: {this.props.car.doors}</Text>
                <Text style={{color: 'white'}}>gear: {this.props.car.gear}</Text>
                <Text style={{color: 'white'}}>aircondition: {this.props.car.aircondition.toString()}</Text>
                <Text style={{color: 'white'}}>location: {this.props.car.location}</Text>
            </View>
        );
    }
}
