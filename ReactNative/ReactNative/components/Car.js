import React, { Component } from 'react';
import {  View, Text, Image, TouchableOpacity} from 'react-native';
import CarInfo from './CarInfo';

export default class componentName extends Component {git
    constructor(props) {
        super(props);
        this.state = {
          showComponent: false, car: undefined
        }
        this._onButtonClick = this._onButtonClick.bind(this);
      }
    
    
      _onButtonClick(event) {
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
    
      test(data) {
        console.log("te");
        this.setState({ car: data })
      }
    
    
      render() {
        return (
          <View className="Car" style={{width: '100%'}}>
            <Image style={{width: '100%', height:100, marginTop: 30}} source={{ uri: this.props.car.picture}}/>
            <View style={{backgroundColor: '#001a28'}}>
              <Text style={{textAlign: 'center', color: 'white'}}>{this.props.car.category}</Text>
                <TouchableOpacity  onPress={this._onButtonClick} href=""><Text style={{textAlign: 'center', color: 'white'}}>Details</Text></TouchableOpacity>
              {this.state.showComponent ?
                <CarInfo car={this.props.car} /> : null
              }
              <Text style={{textAlign: 'center', color: 'white'}}>Price per day: {this.props.car.priceperday}</Text>
              {/*<Link to={`/Booking/${this.props.car.regno}`}>Order Car</Link>*/}
            </View>
          </View>
        )
      }
}
