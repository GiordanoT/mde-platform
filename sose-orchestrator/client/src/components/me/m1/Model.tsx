import React from 'react';
import '../style.scss';
import {RootState} from '../../../redux';
import {Dispatch} from 'redux';
import {connect} from 'react-redux';
import {ReduxAction} from '../../../redux/actions';
import U from '../../../model/u';
import {IObject} from "../../../model/me/interfaces";
import EObject from "./EObject";

function ModelComponent(props: AllProps) {
    const objects = props.objects;
    const id = U.getId('EModel', 1);
    let css = (props.selected === id) ? 'selected' : '';

    const selected = (evt: React.MouseEvent<HTMLDivElement>) => {
        evt.stopPropagation();
        ReduxAction.setSelected(id);
    }

    return(<div id={id} className={'EModel ' + css} onClick={selected}>
        {objects.map((eObject, index: number) => {
            return(<EObject data={eObject} key={index} />)
        })}
    </div>);
}

interface OwnProps {}
interface StateProps {objects: IObject[], selected: string}
interface DispatchProps {}
type AllProps = OwnProps & StateProps & DispatchProps;

function mapStateToProps(state: RootState, ownProps: OwnProps): StateProps {
    const objects = state.raw.model;
    const selected = state.raw.selected;
    return {objects, selected};
}

function mapDispatchToProps(dispatch: Dispatch<any>): DispatchProps {
    const ret: DispatchProps = {};
    return ret;
}


const Model = connect<StateProps, DispatchProps, OwnProps, RootState>(mapStateToProps, mapDispatchToProps)(ModelComponent);
export default Model;
