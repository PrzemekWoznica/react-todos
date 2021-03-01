import { ADD_TODO, GET_TODOS, UPDATE_TODO, DELETE_TODO, REMOVE_ALL, CHANGE_USER } from './reduxConstants';

const initialState = {
    todos: []
};

const todoReducer = (state = initialState, action) => {
    switch(action.type){
        case ADD_TODO:
            return {
                ...state,
                todos: [...state.todos, action.payload]
            };
        case GET_TODOS:
            return {
                ...state,
                todos: action.payload
            };
        case UPDATE_TODO:
            return {
                ...state,
                todos: state.todos.map(todo => {
                    return todo.id !== action.payload.id ? todo : action.payload.data
                })
            };
        case DELETE_TODO:
            return {
                ...state,
                todos: state.todos.filter(todo => (
                    todo.id !== action.payload
                ))
            };
        case REMOVE_ALL:
            return {
                todos: []
            };
        case CHANGE_USER:
            return {
                todos: []
            };
        default:
            return state;
    };
};

export default todoReducer;