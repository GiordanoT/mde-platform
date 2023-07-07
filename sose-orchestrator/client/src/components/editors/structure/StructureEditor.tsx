import React from 'react';
import '../style.scss';
import {RootState} from '../../../redux';
import {Dispatch} from 'redux';
import {connect} from 'react-redux';
import Structures from "./Structures";


function StructureEditorComponent(props: AllProps) {
    const userId = props.userId;
    const selected = props.selected.split('.');
    const classname: string = selected[0];
    const id: number = parseInt(selected[1]);

    switch(classname) {
        case 'EMetamodel': return(<>{Structures.eMetamodel(id, userId)}</>);
        case 'EPackage': return(<>{Structures.ePackage(id, userId)}</>);
        case 'EClass': return(<>{Structures.eClass(id, userId)}</>);
        case 'EAttribute': return(<>{Structures.eAttribute(id, userId)}</>);
        case 'EModel': return(<>{Structures.eModel(id, userId)}</>);
        case 'EObject': return(<>{Structures.eObject(id, userId)}</>);
        case 'EValue': return(<>{Structures.eValue(id, userId)}</>);
        default: return(<div className={'p-2'}>Select a Node...</div>);
    }
}

interface OwnProps {}
interface StateProps {userId: number, selected: string}
interface DispatchProps {}
type AllProps = OwnProps & StateProps & DispatchProps;

function mapStateToProps(state: RootState, ownProps: OwnProps): StateProps {
    const userId = state.raw.userId;
    const selected = state.raw.selected;
    return {userId, selected};
}

function mapDispatchToProps(dispatch: Dispatch<any>): DispatchProps {
    const ret: DispatchProps = {};
    return ret;
}


const StructureEditor = connect<StateProps, DispatchProps, OwnProps, RootState>(mapStateToProps, mapDispatchToProps)(StructureEditorComponent);
export default StructureEditor;
