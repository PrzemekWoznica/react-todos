import React, { useState } from 'react';
import { withRouter } from 'react-router-dom';
import axios from 'axios';

function Signup(props){
    const [username, setUsername] = useState('');

    const handleChange = e => {
        setUsername(e.target.value);
    };

    const handleSignUp = async () => {
        if(username !== '') {
            let user = {username: username};
            await axios.post('/signup', user).then(response => { 
                localStorage.setItem('username', username);
                props.history.push('/home');
            });
        };
    };

    const handleEnter = e => {
        if (e.key === 'Enter') {
            handleSignUp();
        };
    };


    return (
        <div className='input-container'>
            <h1>Sign up</h1>
            <div className='input-field'>
                <input type='text' name='content' onChange={handleChange} onKeyDown={handleEnter} placeholder='Enter your username' autoComplete='off'/>
            </div>
            <div className='input-button' onClick={handleSignUp}>Sign Up</div>
        </div>
    );
};

export default withRouter(Signup);