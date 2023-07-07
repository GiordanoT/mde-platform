import React, {useEffect} from 'react';
import '../style.scss';
import {RootState} from '../../../redux';
import {Dispatch} from 'redux';
import {connect} from 'react-redux';
import U from "../../../model/u";
import Api from "../../../api/Api";
import {useStateIfMounted} from "use-state-if-mounted";
import {ReduxAction} from "../../../redux/actions";
import {Json} from "../../../model/types";
import {JSONTree} from "react-json-tree";


function StructureEditorComponent(props: AllProps) {
    const userId = props.userId;
    const selected = props.selected.split('.');
    const classname: string = selected[0];
    const id: number = parseInt(selected[1]);

    const theme = {
        scheme: 'monokai',
        base00: '#272822',
        base01: '#383830',
        base02: '#49483e',
        base03: '#75715e',
        base04: '#a59f85',
        base05: '#f8f8f2',
        base06: '#f5f4f1',
        base07: '#f9f8f5',
        base08: '#f92672',
        base09: '#fd971f',
        base0A: '#f4bf75',
        base0B: '#a6e22e',
        base0C: '#a1efe4',
        base0D: '#66d9ef',
        base0E: '#ae81ff',
        base0F: '#cc6633',
    };

    const [data, setData] = useStateIfMounted<Json>({});

    const retrieveData = async() => {
        ReduxAction.setLoading(true);
        const crudData = U.getCrudClassname(classname);
        const crud = crudData.crud;
        const isMetamodel = crudData.isMetamodel;
        let url = '';
        if(crud === 'metamodel' || crud === 'model') {
            if(isMetamodel) url = `metamodel/user/${userId}`;
            else url = `model/user/${userId}`;
        } else {
            if (isMetamodel) url = `metamodel/${crud}/${id}`;
            else url = `model/${crud}/${id}`;
        }
        const response = await Api.get(url);
        setData(response.data);
        ReduxAction.setLoading(false);
    }

    switch(classname) {
        case 'EMetamodel':
        case 'EPackage':
        case 'EClass':
        case 'EAttribute':
        case 'EModel':
        case 'EObject':
        case 'EValue':
            return(<div className={'p-2'}>
                <label className={'d-block text-center'}><b>{classname}</b></label>
                <hr />
                <div className={'d-flex border rounded p-1'}>
                    <label className={'ms-2 my-auto'}>Generate Tree</label>
                    <button className={'ms-auto btn btn-primary rounded px-2'} onClick={retrieveData}>
                        <i className={'bi bi-tree-fill'} />
                    </button>
                </div>
                <div className={'d-block p-2'}>
                    {(data) && <JSONTree data={U.removeFields(data, ['id', 'userId'])} theme={theme} invertTheme={true} />}
                </div>
            </div>);
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
