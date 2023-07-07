import React from 'react';
import {RootState} from '../../redux';
import {Dispatch} from 'redux';
import {connect} from 'react-redux';
import './style.scss';
import {Oval} from "react-loader-spinner";

function LoadingComponent(props: AllProps) {
    const loading = props.loading;

    if(loading) {
        return(<div className={'loading '}>
            <Oval height={80} width={80} wrapperStyle={{justifyContent: 'center'}} wrapperClass={'mt-4'}
                  color={'#845EC2'} secondaryColor={'#845EC2'} />
        </div>)
    } else return(<></>);
}

interface OwnProps {}
interface StateProps {loading: boolean}
interface DispatchProps {}
type AllProps = OwnProps & StateProps & DispatchProps;

function mapStateToProps(state: RootState, ownProps: OwnProps): StateProps {
const loading = state.raw.loading;
return {loading};
}

function mapDispatchToProps(dispatch: Dispatch<any>): DispatchProps {
const ret: DispatchProps = {};
return ret;
}


const Loading = connect<StateProps, DispatchProps, OwnProps, RootState>(mapStateToProps, mapDispatchToProps)(LoadingComponent);
export default Loading;
