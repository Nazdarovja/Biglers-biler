import React, { Component } from 'react';
import '../styles/App.css';

export default class Main extends Component {
  render() {
    return (
      <div className="grid-container-main">
        <div className="grid-item flex-container-sidenav">
          <div className="flex-item-sidenav-search">
            Searcher
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
