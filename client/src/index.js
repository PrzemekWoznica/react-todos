import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { Provider } from 'react-redux';
import todoReducer from './redux/todoReducer';
import Routing from './routers/Routing';
import './styles/style.css';

const store = createStore(todoReducer, applyMiddleware(thunk));
ReactDOM.render(
    <Provider store={store}>
      <Routing />
    </Provider>,
  document.getElementById('root')
);
