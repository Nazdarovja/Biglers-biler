import React, { Component } from 'react';
import {View, Text, ScrollView, TouchableOpacity} from 'react-native';
import facade from '../Facade';
//import SideSearch from './SideSearch';
import CarList from './CarList';
import Filter from './Filter';
import Sort from './Sort';

export default class Main extends Component {
  constructor(props) {
    super(props);
    
    this.state = {
      cars: [],
      filteredCars: [],
      facade: facade,
      sortAsc: true,
      error: undefined
    }
    this.findCars = this.findCars.bind(this);
  }

  componentDidMount() {
    this.findCars();
  }

  findCars() {
    const {location, fromdate, todate} = this.props.navigation.state.params
    this.state.facade.fetchCars(fromdate, todate, location)
      .then((res) => {
        const cars = res.cars
        this.setState({ cars: cars, error: undefined, filteredCars: cars })
      }).catch((ex) => this.setState({ error: ex.message + ', ' + ex.status }))
  }


  error() {
    if (this.state.error === undefined) {
      return (
        <View style={{width: '100%'}}>
            <CarList cars={this.state.filteredCars} />
        </View>
      )
    } else {
      return (
        <Text className="alert alert-warning">{this.state.error}</Text>
      )
    }
  }

  filter = (categoryFilters, companyFilters) => {
    if (categoryFilters.length >= 1 && companyFilters.length >= 1) {
      this.filterBothCategoryAndCompany(categoryFilters, companyFilters);
    }
    else if (categoryFilters.length >= 1) {
      this.filterCategory(categoryFilters);
    }
    else {
      this.filterCompany(companyFilters);
    }

  }

  filterCompany = (companyFilters) => {
    const filteredData = this.state.cars.filter((car) => {
      for (var i = 0; i < companyFilters.length; i++) {
        if (car.company.replace(" ", "") === companyFilters[i]) {
          return true;
        }
      }
      return false;
    });
    if (filteredData.length >= 1)
      this.setState({ filteredCars: filteredData });
    else
      this.setState({ filteredCars: this.state.cars });
  }

  filterCategory = (categoryFilters) => {
    const filteredData = this.state.cars.filter((car) => {
      for (var i = 0; i < categoryFilters.length; i++) {
        if (car.category === categoryFilters[i])
          return true;
      }
      return false;
    });

    if (filteredData.length >= 1)
      this.setState({ filteredCars: filteredData });
    else
      this.setState({ filteredCars: this.state.cars });
  }

  filterBothCategoryAndCompany = (categoryFilters, companyFilters) => {
    const filteredData = this.state.cars.filter((car) => {
      var cat = false;
      var com = false;
      for (var i = 0; i < categoryFilters.length; i++) {
        if (car.category === categoryFilters[i])
          cat = true;
      }
      for (var j = 0; j < companyFilters.length; j++) {
        if (car.company.replace(" ", "") === companyFilters[j])
          com = true;
      }
      return cat && com ? true : false;
    });
    if (filteredData.length >= 1)
      this.setState({ filteredCars: filteredData });
    else
      this.setState({ filteredCars: this.state.cars });
  }

  sortingSwitch = () => {
    if (this.state.sortAsc) {
      var list = this.state.filteredCars;
      list.sort((a, b) => {
        return a.priceperday - b.priceperday;
      });
      this.setState({ sortAsc: false, filteredCars: list });
    } else {
      var list = this.state.filteredCars;
      list.sort((a, b) => {
        return b.priceperday - a.priceperday;
      });
      this.setState({ sortAsc: true, filteredCars: list });
    }
  }



  render() {
    const { navigate } = this.props.navigation;
    return (
      <ScrollView style={{width: '100%', marginTop: 40}}>
          <TouchableOpacity 
            // test style
            style={{backgroundColor: '#DDDDDD', padding: 10, alignItems : "center"}}
            onPress={() => navigate('Search')}
            >
            <Text>
                Back 
            </Text>
          </TouchableOpacity>

        <View >
          <View >
            <Filter filter={this.filter} /> 
          </View>
        </View>
        <View style={{width: '100%'}}>
            <Sort sortingSwitch={this.sortingSwitch} />
          <View style={{width: '100%'}}>
            {this.error()}
          </View>
        </View>
      </ScrollView>
    );
  }
}
