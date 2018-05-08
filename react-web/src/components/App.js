import React, { Component } from 'react';
import '../styles/App.css';
import Main from './Main';
import Booking from './Booking';
import { HashRouter as Router, Route, Switch } from 'react-router-dom'
import Startpage from './Startpage';

class App extends Component {

    constructor(props) {
        super(props);
    }

    render() {

        return (
            <Router>
                <Switch>
                    <Route exact path="/" component={Startpage} />
                    <Route exact path="/Main" component={Main} />
                    <Route path="/Booking/:car" component={Booking}/>   
                </Switch>
            </Router>
        );
    }
}

export default App;
