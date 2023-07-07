import React from 'react';
import '../style.scss';
import {RootState} from '../../../redux';
import {Dispatch} from 'redux';
import {connect} from 'react-redux';
import {IValue} from "../../../model/me/interfaces";
import U from "../../../model/u";
import {ReduxAction} from "../../../redux/actions";

function EValueComponent(props: AllProps) {
    const data = props.data;
    const id = U.getId('EValue', data.id);
    let css = (props.selected === id) ? 'selected' : '';

    const selected = (evt: React.MouseEvent<HTMLDivElement>) => {
        evt.stopPropagation();
        ReduxAction.setSelected(id);
    }

    return(<div className={'EValue ' + css} onClick={selected}>
        <label className={'ms-1'}>{data.attributeName}:</label>
        <label className={'ms-auto me-1'}><b>{data.raw}</b></label>
    </div>);
}

interface OwnProps {data: IValue}
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


const EValue = connect<StateProps, DispatchProps, OwnProps, RootState>(mapStateToProps, mapDispatchToProps)(EValueComponent);
export default EValue;
