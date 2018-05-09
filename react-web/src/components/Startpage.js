import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import StartSearch from './StartSearch';

export default class Startpage extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className='grid-container-start'>
                <div className='start-search-container'>
                    <img alt="" src='https://i.imgur.com/t0qqq7l.png'></img>
                    <StartSearch />
                </div>
            </div>
        )
    }
}



