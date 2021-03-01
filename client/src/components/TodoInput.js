import React from 'react';

function TodoInput(props) {
    const handleChange = e => {
        props.handleChange(e.target.value);
    };

    const onKeyDown = e => {
        props.handleEnterPress(e.target, e.key);
    };

    const clearText = e => {
        e.target.value = '';
    }
    
    return (
        <div className='input-field'>
            <input type='text' name='content' onChange={handleChange} onKeyDown={onKeyDown} onClick={clearText} placeholder='Enter your username, then press Enter' autoComplete='off'/>
        </div>
    );
};

export default TodoInput;