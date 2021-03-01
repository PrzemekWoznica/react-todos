import React, { useState, useEffect } from 'react';
import TodoList from './TodoList';
import TodoInput from './TodoInput';
import { connect } from 'react-redux';
import { addTodo, getTodos, changeUser } from '../redux/todoAction';
import { withRouter } from 'react-router-dom';

function App(props){
    const [todoContent, setTodoContent] = useState('');

    useEffect(() => {
        if(localStorage.getItem('username') === '' || localStorage.getItem('username') === null) {
            props.history.push('/');
            
        } else {
            props.todoGet(localStorage.getItem('username'));
        }  
    }, []);

    const handleChange = (todoContent) => {
        setTodoContent(todoContent);
    };

    const handleSubmit = target => {
        if (todoContent !== '') {
            let todo = {
                content: todoContent
            };
            props.todoAdd(todo, localStorage.getItem('username'));
            target.value = '';
        };
    };

    const handleEnterPress = (target, key) => {
        if (key === "Enter") {
            handleSubmit(target);
        };
    };

    const changeUser = () => {
        localStorage.removeItem('username');
        props.history.push('/');
        props.userChange();
    }

    return (
        <div className='todos-container'>
            <h1>Todos</h1>
            <p className='change-user' onClick={changeUser}>Change user</p>
            <TodoInput handleChange={handleChange} handleEnterPress={handleEnterPress} handleSubmit={handleSubmit}/>
            <TodoList todos={props.todos} />
        </div>
    );
};

export default withRouter(connect(
    state => {
        return {
            todos: state.todos
        };
}, dispatch => {
        return {
            todoAdd: (todo, user) => dispatch(addTodo(todo, user)),
            todoGet: user => dispatch(getTodos(user)),
            userChange: () => dispatch(changeUser())
        };
    }
)(App));