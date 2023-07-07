import React from 'react';
import './style.scss';
import {DockLayout, LayoutData} from 'rc-dock';
import Metamodel from '../me/m2/Metamodel';
import Model from '../me/m1/Model';
import {useEffectOnce} from 'usehooks-ts';
import Api from '../../api/Api';
import {ReduxAction} from '../../redux/actions';
import StructureEditor from '../editors/structure/StructureEditor';
import TreeEditor from "../editors/tree/TreeEditor";

interface IProps {userId: number}
function Dock(props: IProps) {

    const groups = {
        'group1': {floatable: true, maximizable: true},
        'group2': {floatable: true, maximizable: true}
    };

    const metamodel = { id: '1', title: 'Metamodel', group: 'group1', closable: false, content: <Metamodel /> };
    const model = { id: '2', title: 'Model', group: 'group1', closable: false, content: <Model /> };
    const structureEditor = { id: '3', title: 'Structure', group: 'group2', closable: false, content: <StructureEditor /> };
    const treeEditor = { id: '4', title: 'Tree', group: 'group2', closable: false, content: <TreeEditor /> };

    const layout: LayoutData = { dockbox: { mode: 'horizontal', children: [] }};
    layout.dockbox.children.push({tabs: [metamodel, model]});
    layout.dockbox.children.push({tabs: [structureEditor, treeEditor]});

    useEffectOnce(() => {
        ReduxAction.setLoading(true);
        const response = Api.get('aggregator/user/' + props.userId);
        response.then((payload) => {
            const metamodel = payload.data['metamodel'];
            ReduxAction.setMetamodel(metamodel);
            const model = payload.data['model'];
            ReduxAction.setModel(model);
            ReduxAction.setLoading(false);
        });
    })

    return (<DockLayout defaultLayout={layout} groups={groups}
                        style={{
                            marginTop: '2.6em',
                            position: 'absolute',
                            left: 5,
                            top: 5,
                            right: 5,
                            bottom: 5
                        }}
    />);


}

export default Dock;
