import React, { Component } from 'react';
import '../styles/App.css';

import SideSearch from './SideSearch';

export default class Main extends Component {
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
            
        </div>
      </div>
    );
  }
}
