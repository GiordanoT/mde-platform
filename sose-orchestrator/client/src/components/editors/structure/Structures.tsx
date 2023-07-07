import React, {ReactNode} from "react";
import Api from "../../../api/Api";
import U from "../../../model/u";
import {DAttribute, DClass, DObject, DPackage, DValue} from "../../../model/me/classes";
import {ReduxAction} from "../../../redux/actions";
import Selectors from "../../../redux/selectors";

export default class Structures {
    static eMetamodel(id: number, userId: number): ReactNode {

        const addChild = async() => {
            try {
                ReduxAction.setLoading(true);

                const ePackage = new DPackage('package_' + U.getIdentifier(), 'it.disim.univaq.sose', userId);
                await Api.post('package', ePackage.json());

                const response = await Api.get('metamodel/user/' + userId);
                ReduxAction.setMetamodel(response.data);
                ReduxAction.setLoading(false);
            } catch (e) { alert('Network Error!'); }
        }

        return(<div className={'p-2'}>
            <label className={'d-block text-center'}><b>EMetamodel</b></label>
            <hr />
            <div className={'d-flex border rounded p-1'}>
                <label className={'my-auto'}>EPackage</label>
                <button className={'ms-auto btn btn-success rounded px-2'} onClick={addChild}>
                    <i className={'bi bi-plus'} />
                </button>
            </div>
        </div>);
    }

    static ePackage(id: number, userId: number) {

        const data = Selectors.getPackage(id);

        const remove = async() => {
            ReduxAction.setLoading(true);
            ReduxAction.setSelected('');
            await Api.delete('package/id/' + id);
            const response = await Api.get('metamodel/user/' + userId);
            ReduxAction.setMetamodel(response.data);
            ReduxAction.setLoading(false);
        }

        const addChild = async() => {
            ReduxAction.setLoading(true);
            const eClass = new DClass('class_' + U.getIdentifier(), false, id, userId);
            await Api.post('class', eClass.json());
            const response = await Api.get('metamodel/user/' + userId);
            ReduxAction.setMetamodel(response.data);
            ReduxAction.setLoading(false);
        }

        const edit = async(evt: React.FormEvent<HTMLFormElement>) => {
            evt.preventDefault();
            ReduxAction.setLoading(true);
            const targets: any = evt.target;
            const name = targets[0].value;
            const uri = targets[1].value;
            const ePackage = new DPackage(name, uri, userId);
            await Api.put('package', {...ePackage.json(), id: id});
            const response = await Api.get('metamodel/user/' + userId);
            ReduxAction.setMetamodel(response.data);
            ReduxAction.setLoading(false);
        }

        return(<div className={'p-2'}>
            <label className={'d-block text-center'}><b>EPackage</b></label>
            <hr />
            <div className={'d-flex border rounded p-1'}>
                <button className={'ms-auto btn btn-danger rounded px-2'} onClick={remove}>
                    <i className={'bi bi-trash-fill'} />
                </button>
            </div>
            <div className={'mt-2 d-flex border rounded p-1'}>
                <label className={'my-auto'}>EClass</label>
                <button className={'ms-auto btn btn-success rounded px-2'} onClick={addChild}>
                    <i className={'bi bi-plus'} />
                </button>
            </div>
            <form className={'mt-2 mx-auto border rounded p-1'} onSubmit={edit}>
                <div className={'d-flex'}>
                    <input key={U.getId('EPackage', id) + '.name'} required={true} className={'input d-block'} defaultValue={data.name} type={'text'} />
                    <label className={'ms-2 my-auto'}>Name</label>
                </div>
                <div className={'d-flex mt-1'}>
                    <input key={U.getId('EPackage', id) + '.uri'} required={false} className={'input d-block'} defaultValue={data.uri} type={'text'} />
                    <label className={'ms-2 my-auto'}>Uri</label>
                </div>
                <button className={'btn btn-success d-block mt-1'} type={'submit'}>Edit</button>
            </form>
        </div>);
    }

    static eClass(id: number, userId: number) {

        const tuple = Selectors.getClass(id);
        const data = tuple.data;
        const packageId = tuple.packageId;

        const remove = async() => {
            ReduxAction.setLoading(true);
            ReduxAction.setSelected('');
            await Api.delete('class/id/' + id);
            const responseM2 = await Api.get('metamodel/user/' + userId);
            ReduxAction.setMetamodel(responseM2.data);
            const responseM1 = await Api.get('model/user/' + userId);
            ReduxAction.setModel(responseM1.data);
            ReduxAction.setLoading(false);
        }

        const addChild = async() => {
            ReduxAction.setLoading(true);
            const eAttribute = new DAttribute('attribute_' + U.getIdentifier(), 'String', 'test', id, userId);
            const response = await Api.post('attribute', eAttribute.json());
            const eAttributeId = response.data;

            const eObjects = await Api.get('object/instanceOf/' + id);
            for(let eObject of eObjects.data) {
                const eValue = new DValue(eAttributeId, '', eObject.id, eObject.userId);
                await Api.post('value', eValue.json());
            }

            const responseM2 = await Api.get('metamodel/user/' + userId);
            ReduxAction.setMetamodel(responseM2.data);
            const responseM1 = await Api.get('model/user/' + userId);
            ReduxAction.setModel(responseM1.data);
            ReduxAction.setLoading(false);
        }

        const edit = async(evt: React.FormEvent<HTMLFormElement>) => {
            evt.preventDefault();
            ReduxAction.setLoading(true);
            const targets: any = evt.target;
            const name = targets[0].value;
            const isAbstract = targets[1].value === '1';
            const eClass = new DClass(name, isAbstract, packageId, userId);
            await Api.put('class', {...eClass.json(), id: id});
            const responseM2 = await Api.get('metamodel/user/' + userId);
            ReduxAction.setMetamodel(responseM2.data);
            const responseM1 = await Api.get('model/user/' + userId);
            ReduxAction.setModel(responseM1.data);
            ReduxAction.setLoading(false);
        }

        return(<div className={'p-2'}>
            <label className={'d-block text-center'}><b>EClass</b></label>
            <hr />
            <div className={'d-flex border rounded p-1'}>
                <button className={'ms-auto btn btn-danger rounded px-2'} onClick={remove}>
                    <i className={'bi bi-trash-fill'} />
                </button>
            </div>
            <div className={'mt-2 d-flex border rounded p-1'}>
                <label className={'my-auto'}>EAttribute</label>
                <button className={'ms-auto btn btn-success rounded px-2'} onClick={addChild}>
                    <i className={'bi bi-plus'} />
                </button>
            </div>
            <form className={'mt-2 mx-auto border rounded p-1'} onSubmit={edit}>
                <div className={'d-flex'}>
                    <input key={U.getId('EClass', id) + '.name'} required={true} className={'input d-block'} defaultValue={data.name} type={'text'} />
                    <label className={'ms-2 my-auto'}>Name</label>
                </div>
                <div className={'d-flex mt-1'}>
                    <select key={U.getId('EClass', id) + '.isAbstract'} className={'select d-block'} defaultValue={(data.abstract) ? 1 : 0}>
                        <option value={1}>True</option>
                        <option value={0}>False</option>
                    </select>
                    <label className={'ms-2 my-auto'}>Is Abstract</label>
                </div>
                <button className={'btn btn-success d-block mt-1'} type={'submit'}>Edit</button>
            </form>
        </div>);
    }

    static eAttribute(id: number, userId: number) {

        const tuple = Selectors.getAttribute(id);
        const data = tuple.data;
        const classId = tuple.classId;

        const remove = async() => {
            ReduxAction.setLoading(true);
            ReduxAction.setSelected('');
            await Api.delete('attribute/id/' + id);
            const responseM2 = await Api.get('metamodel/user/' + userId);
            ReduxAction.setMetamodel(responseM2.data);
            const responseM1 = await Api.get('model/user/' + userId);
            ReduxAction.setModel(responseM1.data);
            ReduxAction.setLoading(false);
        }

        const edit = async(evt: React.FormEvent<HTMLFormElement>) => {
            evt.preventDefault();
            ReduxAction.setLoading(true);
            const targets: any = evt.target;
            const name = targets[0].value;
            const type = targets[1].value;
            const defaultValue = targets[2].value;
            const eAttribute = new DAttribute(name, type, defaultValue, classId, userId);
            await Api.put('attribute', {...eAttribute.json(), id: id});
            const responseM2 = await Api.get('metamodel/user/' + userId);
            ReduxAction.setMetamodel(responseM2.data);
            const responseM1 = await Api.get('model/user/' + userId);
            ReduxAction.setModel(responseM1.data);
            ReduxAction.setLoading(false);
        }

        return(<div className={'p-2'}>
            <label className={'d-block text-center'}><b>EAttribute</b></label>
            <hr />
            <div className={'d-flex border rounded p-1'}>
                <button className={'ms-auto btn btn-danger rounded px-2'} onClick={remove}>
                    <i className={'bi bi-trash-fill'} />
                </button>
            </div>
            <form className={'mt-2 mx-auto border rounded p-1'} onSubmit={edit}>
                <div className={'d-flex'}>
                    <input key={U.getId('EAttribute', id) + '.name'} required={true} className={'input d-block'} defaultValue={data.name} type={'text'} />
                    <label className={'ms-2 my-auto'}>Name</label>
                </div>
                <div className={'d-flex mt-1'}>
                    <select key={U.getId('EAttribute', id) + '.type'} className={'select d-block'} defaultValue={data.type}>
                        <option value={'Boolean'}>Boolean</option>
                        <option value={'Number'}>Number</option>
                        <option value={'Char'}>Char</option>
                        <option value={'String'}>String</option>
                    </select>
                    <label className={'ms-2 my-auto'}>Type</label>
                </div>
                <div className={'d-flex mt-1'}>
                    <input key={U.getId('EAttribute', id) + '.defaultValue'} required={true} className={'input d-block'} defaultValue={data.defaultValue} type={'text'} />
                    <label className={'ms-2 my-auto'}>Default Value</label>
                </div>
                <button className={'btn btn-success d-block mt-1'} type={'submit'}>Edit</button>
            </form>
        </div>);
    }

    static eModel(id: number, userId: number): ReactNode {

        const data = Selectors.getClasses().filter(obj => !obj.abstract);

        const addChild = async(classId: number) => {
            ReduxAction.setLoading(true);

            const eObject = new DObject(classId, userId);
            await Api.post('object', eObject.json());

            const response = await Api.get('model/user/' + userId);
            ReduxAction.setModel(response.data);
            ReduxAction.setLoading(false);

        }

        return(<div className={'p-2'}>
            <label className={'d-block text-center'}><b>EModel</b></label>
            <hr />
            {data.map((eClass, index) => {
                return(<div key={index} className={'d-flex border rounded p-1'}>
                    <label className={'my-auto'}>{eClass.name}</label>
                    <button className={'ms-auto btn btn-success rounded px-2'} onClick={() => addChild(eClass.id)}>
                        <i className={'bi bi-plus'} />
                    </button>
                </div>);
            })}

        </div>);
    }

    static eObject(id: number, userId: number) {

        const remove = async() => {
            ReduxAction.setLoading(true);
            ReduxAction.setSelected('');
            await Api.delete('object/id/' + id);
            const response = await Api.get('model/user/' + userId);
            ReduxAction.setModel(response.data);
            ReduxAction.setLoading(false);
        }

        const conforms = async() => {
            ReduxAction.setLoading(true);
            const response = await Api.get('object/' + id + '/conforms');
            if(response.data) alert('The selected EObject is conform to his instanceof class.');
            else alert('The selected EObject is NOT conform to his instanceof class.');
            ReduxAction.setLoading(false);
        }


        return(<div className={'p-2'}>
            <label className={'d-block text-center'}><b>EObject</b></label>
            <hr />
            <div className={'d-flex border rounded p-1'}>
                <button className={'ms-auto btn btn-danger rounded px-2'} onClick={remove}>
                    <i className={'bi bi-trash-fill'} />
                </button>
            </div>
            <div className={'mt-1 d-flex border rounded p-1'}>
                <label className={'my-auto'}>Check Conformance</label>
                <button className={'ms-auto btn btn-success rounded px-2'} onClick={conforms}>
                    <i className={'bi bi-question-lg'} />
                </button>
            </div>
        </div>);
    }

    static eValue(id: number, userId: number) {
        const tuple = Selectors.getValue(id);
        const data = tuple.data;
        const objectId = tuple.objectId;
        const instanceOf = Selectors.getInstanceOf(data.attributeName);

        const edit = async(evt: React.FormEvent<HTMLFormElement>) => {
            evt.preventDefault();
            ReduxAction.setLoading(true);
            const targets: any = evt.target;
            const raw = targets[0].value;
            const eValue = new DValue(instanceOf, raw, objectId, userId);
            await Api.put('value', {...eValue.json(), id: id});
            const response = await Api.get('model/user/' + userId);
            ReduxAction.setModel(response.data);
            ReduxAction.setLoading(false);
        }

        return(<div className={'p-2'}>
            <label className={'d-block text-center'}><b>EValue</b></label>
            <hr />
            <form className={'mx-auto border rounded p-1'} onSubmit={edit}>
                <div className={'d-flex'}>
                    <input key={U.getId('EValue', id) + '.raw'} required={true} className={'input d-block'} defaultValue={data.raw} type={'text'} />
                    <label className={'ms-2 my-auto'}>Raw</label>
                </div>
                <button className={'btn btn-success d-block mt-1'} type={'submit'}>Edit</button>
            </form>
        </div>);
    }
}
