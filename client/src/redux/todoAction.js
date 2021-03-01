import { ADD_TODO, GET_TODOS, UPDATE_TODO, DELETE_TODO, REMOVE_ALL, CHANGE_USER } from './reduxConstants';
import Axios from 'axios';

export const addTodo = (todo, user) => async dispatch => {
    await Axios.post(`/api/${user}`, todo)
    .then(response => {
        dispatch({
            type: ADD_TODO,
            payload: response.data
        });
    });
};

export const getTodos = user => async dispatch => {
    await Axios.get(`/api/${user}`)
    .then(response => {
        dispatch({
            type: GET_TODOS,
            payload: response.data
        });
    });
};

export const updateTodo = (id, user) => async dispatch => {
    await Axios.put(`/api/${user}/todo/${id}`)
    .then(response => {
        dispatch({
            type: UPDATE_TODO,
            payload: {
                data: response.data,
                id: id
            }
        });
    });
};

export const deleteTodo = (id, user) => async dispatch => {
    await Axios.delete(`/api/${user}/todo/${id}`)
    .then(response => {
        dispatch({
            type: DELETE_TODO,
            payload: id
        });
    });
};

export const deleteAll = user =>  async dispatch => {
    await Axios.delete(`/api/${user}`)
    .then(response => {
        dispatch({
            type: REMOVE_ALL
        });
    });
};

export const changeUser = () => {return {type: CHANGE_USER}};