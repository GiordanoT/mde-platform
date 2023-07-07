import React from 'react';
import '../style.scss';
import {RootState} from '../../../redux';
import {Dispatch} from 'redux';
import {connect} from 'react-redux';
import {ReduxAction} from '../../../redux/actions';
import U from '../../../model/u';
import EPackage from "./EPackage";
import {IPackage} from "../../../model/me/interfaces";

function MetamodelComponent(props: AllProps) {
    const packages = props.packages;
    const id = U.getId('EMetamodel', 1);
    let css = (props.selected === id) ? 'selected' : '';

    const selected = (evt: React.MouseEvent<HTMLDivElement>) => {
        evt.stopPropagation();
        ReduxAction.setSelected(id);
    }

    return(<div id={id} className={'EModel ' + css} onClick={selected}>
        {packages.map((ePackage: IPackage, index: number) => {
            return(<EPackage data={ePackage} key={index} />)
        })}
    </div>);
}

interface OwnProps {}
interface StateProps {packages: IPackage[], selected: string}
interface DispatchProps {}
type AllProps = OwnProps & StateProps & DispatchProps;

function mapStateToProps(state: RootState, ownProps: OwnProps): StateProps {
    const packages = state.raw.metamodel;
    const selected = state.raw.selected;
    return {packages, selected};
}

function mapDispatchToProps(dispatch: Dispatch<any>): DispatchProps {
    const ret: DispatchProps = {};
    return ret;
}


const Metamodel = connect<StateProps, DispatchProps, OwnProps, RootState>(mapStateToProps, mapDispatchToProps)(MetamodelComponent);
export default Metamodel;
