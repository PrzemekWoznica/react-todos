import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import Login from '../components/Login';
import Signup from '../components/Signup';
import App from '../components/App';

function Routing() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path='/home'>
                    <App />
                </Route>
                <Route exact path='/signup'>
                    <Signup />
                </Route>
                <Route exact path='/'>
                    <Login />
                </Route>
            </Switch>
        </BrowserRouter>
    );
};

export default Routing;