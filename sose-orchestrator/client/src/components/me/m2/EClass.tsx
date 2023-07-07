import React from 'react';
import '../style.scss';
import {RootState} from '../../../redux';
import {Dispatch} from 'redux';
import {connect} from 'react-redux';
import {IClass} from "../../../model/me/interfaces";
import U from "../../../model/u";
import {ReduxAction} from "../../../redux/actions";
import EAttribute from "./EAttribute";
import {useEffectOnce} from "usehooks-ts";
import $ from "jquery";

function EClassComponent(props: AllProps) {
    const data = props.data;
    const id = U.getId('EClass', data.id);
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

    return(<div id={id} className={'EClass rounded ' + css} onClick={selected}>
        <b className={'name ms-1'}>EClass</b>: {data.name}
        <hr className={'my-1'} />
        {data.attributes.map((eAttribute, index) => {
            return(<EAttribute data={eAttribute} key={index} />)
        })}
    </div>);
}

interface OwnProps {data: IClass}
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


const EClass = connect<StateProps, DispatchProps, OwnProps, RootState>(mapStateToProps, mapDispatchToProps)(EClassComponent);
export default EClass;
