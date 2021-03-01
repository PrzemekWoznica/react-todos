import React from 'react';
import Todo from './Todo';
import { connect } from 'react-redux';
import { deleteAll } from '../redux/todoAction';

function TodoList(props) {
    const handleRemove = () => {
        props.allDelete(localStorage.getItem('username'));
    }
    let { todos } = props;
    let content = '';
    if (todos.length === 0) {
        content =
        <div className='todo-list-no-todos'>
            <p>No todos...</p>
        </div>;
        
    } else {
        content = 
        <div className='todo-list'>
            <div className='remove-all-todos' onClick={handleRemove}>
                <p>Remove all todos</p>
            </div>
            <div className='todos'>
                {todos.map(todo => 
                        <Todo key={todo.id} todo={todo} />
                    )
                }
            </div>
        </div>;
    };
    return content;
};

export default connect(null, dispatch => {
    return {
        allDelete: user => dispatch(deleteAll(user))
    }
})(TodoList);