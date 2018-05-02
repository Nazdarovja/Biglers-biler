import React, { Component } from 'react';
import '../styles/App.css';

import facade from '../Facade';

import SideSearch from './SideSearch';
import CarList from './CarList';



export default class Main extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cars: [],
      error: undefined
    }
  }

  componentDidMount() {
    facade.fetchData()
      .then((res) => {
        this.setState({ cars: res, error: undefined })
      }).catch((ex) => this.setState({ error: ex.message + ', ' + ex.status }))
  }

  error() {
    if (this.state.error === undefined) {
      return (
        <CarList cars={this.state.cars} />
      )
    } else {
      return (
        <p className="alert alert-warning">{this.state.error}</p>
      )
    }
  }

  render() {
    return (
      <div className="grid-container-main">
        <div className="grid-item flex-container-sidenav">
          <div className="flex-item-sidenav-search">
            <SideSearch />
          </div>
          <div className="flex-item-sidenav-filter">
            Filter
          </div>
        </div>
        <div className="grid-item">
          <div className="flex-container-content">


            {this.error()}


            {/* <CarList cars={this.state.cars}/> */}
          </div>
        </div>
      </div>
    );
  }
}

// const cars = [

//   {
//   // "logo": "https://imgur.com/t0qqq7l",
//   "logo":"https://i.imgur.com/t0qqq7l.png",
//   "company": "Biglers Bigler",
//   "category": "Mini",
//   "picture": "https://icdn5.digitaltrends.com/image/2015-mini-cooper-s-hardtop-0018-800x533-c.jpg",
//   "make": "Mini Cooper",
//   "model": "Mini Cooper S",
//   "year": 2017,
//   "regno": "BUF 9330",
//   "seats": 5,
//   "doors": 5,
//   "gear": "Manual",
//   "aircondition": false,
//   "location": "Copenhagen City",
//   "priceperday": 900,
//   "reservations": [
//       {
//           "companyTag": "Biglers biler",
//           "customerMail": "y@Cooper.dk",
//           "fromDate": "01/01/2018",
//           "toDate": "14/01/2018"
//       },
//       {
//           "companyTag": "Biglers biler",
//           "customerMail": "y@ss.dk",
//           "fromDate": "15/02/2018",
//           "toDate": "15/03/2018"
//       }
//   ]
//   },

//   {
//       // "logo": "https://imgur.com/t0qqq7l",
//       "logo":"https://i.imgur.com/t0qqq7l.png",
//       "company": "Biglers Bigler",
//       "category": "Mini",
//       "picture": "https://icdn5.digitaltrends.com/image/2015-mini-cooper-s-hardtop-0018-800x533-c.jpg",
//       "make": "Mini Cooper",
//       "model": "Mini Cooper S",
//       "year": 2017,
//       "regno": "BUF 9330",
//       "seats": 5,
//       "doors": 5,
//       "gear": "Manual",
//       "aircondition": false,
//       "location": "Copenhagen City",
//       "priceperday": 900,
//       "reservations": [
//           {
//               "companyTag": "Biglers biler",
//               "customerMail": "y@Cooper.dk",
//               "fromDate": "01/01/2018",
//               "toDate": "14/01/2018"
//           },
//           {
//               "companyTag": "Biglers biler",
//               "customerMail": "y@ss.dk",
//               "fromDate": "15/02/2018",
//               "toDate": "15/03/2018"
//           }
//       ]
//       },

//       {
//           // "logo": "https://imgur.com/t0qqq7l",
//           "logo":"https://i.imgur.com/t0qqq7l.png",
//           "company": "Biglers Bigler",
//           "category": "Mini",
//           "picture": "https://icdn5.digitaltrends.com/image/2015-mini-cooper-s-hardtop-0018-800x533-c.jpg",
//           "make": "Mini Cooper",
//           "model": "Mini Cooper S",
//           "year": 2017,
//           "regno": "BUF 9330",
//           "seats": 5,
//           "doors": 5,
//           "gear": "Manual",
//           "aircondition": false,
//           "location": "Copenhagen City",
//           "priceperday": 900,
//           "reservations": [
//               {
//                   "companyTag": "Biglers biler",
//                   "customerMail": "y@Cooper.dk",
//                   "fromDate": "01/01/2018",
//                   "toDate": "14/01/2018"
//               },
//               {
//                   "companyTag": "Biglers biler",
//                   "customerMail": "y@ss.dk",
//                   "fromDate": "15/02/2018",
//                   "toDate": "15/03/2018"
//               }
//           ]
//           }
// ];