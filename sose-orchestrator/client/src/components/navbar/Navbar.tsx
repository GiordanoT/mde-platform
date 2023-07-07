import React from 'react';
import {ReduxAction} from '../../redux/actions';
import './style.scss';

interface IProps {props: IProps}
function Navbar() {
    return(<div className={'navbar'} style={{backgroundColor: 'indigo'}}>
        <label className={'ms-3'} style={{color: 'whitesmoke'}}><b>SOSE Project</b></label>
        <button className={'btn btn-danger ms-auto me-3'} onClick={() => ReduxAction.setUserId(0)}>Logout</button>
    </div>);
}

export default Navbar;
