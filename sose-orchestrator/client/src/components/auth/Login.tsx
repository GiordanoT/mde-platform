import React, {useState} from 'react';
import './style.scss';
import Api from '../../api/Api';
import {useStateIfMounted} from 'use-state-if-mounted';
import {ReduxAction} from "../../redux/actions";

function Login() {
    const [username, setUsername] = useStateIfMounted('');
    const [password, setPassword] = useStateIfMounted('');
    const [error, setError] = useStateIfMounted(false);

    const click = async() => {
        if(!username || !password) { setError(true); return; }
        try {
            ReduxAction.setLoading(true);
            const response = await Api.post('user/auth', {username, password});
            setError(false);
            ReduxAction.setUserId(response.data);
        }
        catch (e) {setError(true)}
        ReduxAction.setLoading(false);
    }

    return(<>
        <label className={'d-block text-center'} style={{fontSize: '1.1rem'}}>LOGIN</label>
        {error && <label className={'mt-1 d-block text-center text-danger'}>ERROR...</label>}
        <input placeholder={'Username'} className={'mx-auto mt-1 input d-block'} type={'text'} onChange={(evt) => setUsername(evt.target.value)} />
        <input placeholder={'Password'} className={'mx-auto mt-1 input d-block'} type={'password'} onChange={(evt) => setPassword(evt.target.value)} />
        <button className={'mx-auto mt-3 d-block btn btn-success'} onClick={click}>Submit</button>
    </>);
}

export default Login;
