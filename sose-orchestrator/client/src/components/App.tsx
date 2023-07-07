import React from 'react';
import Dock from './dock/Dock';
import Navbar from './navbar/Navbar';
import {RootState} from '../redux';
import {Dispatch} from 'redux';
import {connect} from 'react-redux';
import Auth from './auth/Auth';
import Loading from "./loading/Loading";

function AppComponent(props: AllProps) {
    const userId = props.userId;
    const loading = props.loading;

    if(userId) {
        return(<>
            {loading && <Loading />}
            <Navbar />
            <Dock userId={userId} />
        </>);
    } else {
        return(<>
            {loading && <Loading />}
            <Auth />
        </>);
    }

}

interface OwnProps {}
interface StateProps {loading: boolean, userId: number}
interface DispatchProps {}
type AllProps = OwnProps & StateProps & DispatchProps;

function mapStateToProps(state: RootState, ownProps: OwnProps): StateProps {
    const loading = state.raw.loading;
    const userId = state.raw.userId;
    return {loading, userId};
}

function mapDispatchToProps(dispatch: Dispatch<any>): DispatchProps {
    const ret: DispatchProps = {};
    return ret;
}


const App = connect<StateProps, DispatchProps, OwnProps, RootState>(mapStateToProps, mapDispatchToProps)(AppComponent);
export default App;
