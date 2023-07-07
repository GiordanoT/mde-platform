import React from 'react';
import '../style.scss';
import {RootState} from '../../../redux';
import {Dispatch} from 'redux';
import {connect} from 'react-redux';
import {IObject} from "../../../model/me/interfaces";
import {ReduxAction} from "../../../redux/actions";
import {useEffectOnce} from "usehooks-ts";
import $ from "jquery";
import U from "../../../model/u";
import EValue from "./EValue";

function EObjectComponent(props: AllProps) {
    const data = props.data;
    const id = U.getId('EObject', data.id);
    let css = (props.selected === id) ? 'selected' : '';

    const selected = (evt: React.MouseEvent<HTMLDivElement>) => {
        evt.stopPropagation();
        ReduxAction.setSelected(id);
    }


    useEffectOnce(() => {
        const node: any = $('[id="' + id + '"]');
        node.draggable({cursor: 'grabbing', containment: 'parent'});
        node.resizable({containment: 'parent'});
    })

    return(<div id={id} className={'EObject rounded  ' + css} onClick={selected}>
        <b className={'name ms-1'}>{data.className}</b>
        <hr className={'my-1'} />
        {data.values.map((eValue, index) => {
            return(<EValue data={eValue} key={index} />)
        })}
    </div>);
}

interface OwnProps {data: IObject}
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


const EObject = connect<StateProps, DispatchProps, OwnProps, RootState>(mapStateToProps, mapDispatchToProps)(EObjectComponent);
export default EObject;
