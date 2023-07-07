import React from 'react';
import './style.scss';
import {useStateIfMounted} from 'use-state-if-mounted';
import Login from './Login';
import Registration from './Registration';

function Auth() {
    const [login, setLogin] = useStateIfMounted(true);
    return(<div className={'mx-auto w-25 rounded mt-4 p-3 auth'}>
        {(login) ? <>
            <Login />
            <label className={'d-block text-center mt-2'}>
                Don't have an account <a onClick={() => setLogin(false)} className={'link'}>click here</a>.
            </label>
        </> : <>
            <Registration />
            <label className={'d-block text-center mt-2'}>
                Already have an account <a onClick={() => setLogin(true)} className={'link'}>click here</a>.
            </label>
        </>}
    </div>);
}

export default Auth;
