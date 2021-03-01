import React from 'react';

function TodoInput(props) {
    const handleChange = e => {
        props.handleChange(e.target.value);
    };

    const onKeyDown = e => {
        props.handleEnterPress(e.target, e.key);
    };

    const handleClick = e => {
        props.handleAdd(e.target);
    };

    const clearText = e => {
        e.target.value = '';
    };
    
    return (
        <>
            <div className='input-field'>
                <input type='text' name='content' onChange={handleChange} onKeyDown={onKeyDown} onClick={clearText} placeholder='Enter todo' autoComplete='off'/>
            </div>
            <div className='input-button input-button-todo' onClick={handleClick}>Add</div>
        </>
        
    );
};

export default TodoInput;