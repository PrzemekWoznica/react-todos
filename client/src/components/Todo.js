import React from 'react';
import { connect } from 'react-redux';
import { getTodos, deleteTodo, updateTodo } from '../redux/todoAction';

function Todo(props) {
    const handleUpdate = () => {
        props.todoUpdate(props.todo.id, localStorage.getItem('username'));
    };

    const handleDelete = () => {
        props.todoDelete(props.todo.id, localStorage.getItem('username'));
    };

    let { todo } = props
    if (todo.checked === false) {
        return (
            <div className='todo'>
                <div>{todo.content}</div>
                <div className='buttons'>
                    <i className="fas fa-edit" id='edit' onClick={handleUpdate} title='Edit'></i>
                    <i className="fas fa-times-circle" id='delete' onClick={handleDelete} title='Delete'></i>
                </div>
            </div>
        );
    } else {
        return (
            <div className='todo-denied'>
                <div>{todo.content}</div>
                <div className='buttons'>
                    <i className="fas fa-edit" id='edit' onClick={handleUpdate}></i>
                    <i className="fas fa-times-circle" id='delete' onClick={handleDelete}></i>
                </div>
            </div>
        );
    };
};

export default connect(null, dispatch => {
    return {
        todoGet: user => dispatch(getTodos(user)),
        todoDelete: (id, user) => dispatch(deleteTodo(id, user)),
        todoUpdate: (id, user) => dispatch(updateTodo(id, user))
    }
})(Todo);