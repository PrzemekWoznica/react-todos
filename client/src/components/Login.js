import React, { useState } from 'react';
import { withRouter } from 'react-router-dom';
import axios from 'axios';

function Login(props){
    const [username, setUsername] = useState('');

    const handleChange = e => {
        setUsername(e.target.value);
    };

    const handleLogin = async () => {
        if (username !== '') {
            let user = {username: username};
            await axios.post('/login', user).then(response => {
                if(response.data.authenticated === true) {
                    localStorage.setItem('username', username);
                    props.history.push('/home');
                } else {
                    localStorage.removeItem('username');
                    props.history.push('/signup');
                };
            });
        };
    };

    const handleEnter = e => {
        if (e.key === 'Enter') {
            handleLogin();
        };
    };

    const signUp = () => {
        props.history.push('/signup');
    };


    return (
        <div className='input-container'>
            <h1>Login</h1>
            <div className='input-field'>
                <input type='text' name='content' onChange={handleChange} onKeyDown={handleEnter} placeholder='Enter your username, then press Enter' autoComplete='off'/>
            </div>
            <p className='or'>or</p>
            <p className='signup-link-button' onClick={signUp}>Sign up</p>
        </div>
    );
};

export default withRouter(Login);