import React, { Component } from 'react';
import {  View, Text, Image} from 'react-native';

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
          <View className="Car">
            <Text className="Car-header">{this.props.car.category}</Text>
            <Image className="Car-picture" alt="" src={this.props.car.picture}/>
            <Text className="Car-link">
              <TouchableOpacity onPress={this._onButtonClick} href=""><Text>Details</Text></TouchableOpacity>
            </Text>
            {this.state.showComponent ?
              <CarInfo car={this.props.car} /> : null
            }
            <Text className="Car-price">Price per day: {this.props.car.priceperday}</Text>
            {/*<Link to={`/Booking/${this.props.car.regno}`}>Order Car</Link>*/}
          </View>
        )
      }
}
