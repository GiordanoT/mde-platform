import React from 'react';
import '../style.scss';
import {RootState} from '../../../redux';
import {Dispatch} from 'redux';
import {connect} from 'react-redux';
import {IPackage} from "../../../model/me/interfaces";
import U from "../../../model/u";
import {ReduxAction} from "../../../redux/actions";
import {useEffectOnce} from "usehooks-ts";
import $ from 'jquery';
import 'jqueryui';
import 'jqueryui/jquery-ui.css';
import EClass from "./EClass";

function EPackageComponent(props: AllProps) {
    const data = props.data;
    const id = U.getId('EPackage', data.id);
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

    return(<div id={id} className={'EPackage rounded ' + css} onClick={selected}>
        <b className={'name ms-1'}>EPackage</b>: {data.name}
        <hr className={'my-1'} />
        {data.classes.map((eClass, index) => {
            return(<EClass data={eClass} key={index} />)
        })}
    </div>);

}

interface OwnProps {data: IPackage}
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


const EPackage = connect<StateProps, DispatchProps, OwnProps, RootState>(mapStateToProps, mapDispatchToProps)(EPackageComponent);
export default EPackage;
