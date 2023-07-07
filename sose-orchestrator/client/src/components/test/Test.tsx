import React from 'react';
import './style.scss';
import {RootState} from '../../redux';
import {Dispatch} from 'redux';
import {connect} from 'react-redux';

function TestComponent(props: AllProps) {

    return(<div>
        Test
    </div>);
}

interface OwnProps {}
interface StateProps {selected: string}
interface DispatchProps {}
type AllProps = OwnProps & StateProps & DispatchProps;

function mapStateToProps(state: RootState, ownProps: OwnProps): StateProps {
    const selected = state.raw.selected;
    return {selected};
}

function mapDispatchToProps(dispatch: Dispatch<any>): DispatchProps {
    const ret: DispatchProps = {};
    return ret;
}


const Test = connect<StateProps, DispatchProps, OwnProps, RootState>(mapStateToProps, mapDispatchToProps)(TestComponent);
export default Test;
