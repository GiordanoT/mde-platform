import {store} from "../index";
import {IAttribute, IClass, IPackage, IValue} from "../../model/me/interfaces";

interface HelperClass {data: IClass, packageId: number}
interface HelperAttribute {data: IAttribute, classId: number}
interface HelperValue {data: IValue, objectId: number}

export default class Selectors {
    static getPackage(id: number): IPackage {
        const state = store.getState().raw;
        const packages = state.metamodel;
        return packages.filter((obj) => { return obj.id === id})[0];
    }

    static getClass(id: number): HelperClass {
        const state = store.getState().raw;
        const packages = state.metamodel;
        let data: IClass|null = null;
        let packageId: number = 0;
        for(let ePackage of packages) {
            const eClass = ePackage.classes.filter((obj) => { return obj.id === id});
            if(eClass.length > 0) {data = eClass[0]; packageId = ePackage.id; break;}
        }
        return {data, packageId} as any;
    }

    static getAttribute(id: number): HelperAttribute {
        const state = store.getState().raw;
        const packages = state.metamodel;
        let data: IAttribute|null = null;
        let classId: number = 0;
        for(let ePackage of packages) {
            if(classId) break;
            for(let eClass of ePackage.classes) {
                const eAttribute = eClass.attributes.filter((obj) => { return obj.id === id});
                if(eAttribute.length > 0) {data = eAttribute[0]; classId = eClass.id; break;}
            }
        }
        return {data, classId} as any;
    }

    static getClasses(): IClass[] {
        const state = store.getState().raw;
        const packages = state.metamodel;
        return packages.map((ePackage) => {return ePackage.classes}).flat();
    }

    static getValue(id: number): HelperValue {
        const state = store.getState().raw;
        const objects = state.model;
        let data: IValue|null = null;
        let objectId: number = 0;
        for(let eObject of objects) {
            const eValue = eObject.values.filter((obj) => { return obj.id === id});
            if(eValue.length > 0) {data = eValue[0]; objectId = eObject.id; break;}
        }
        return {data, objectId} as any;
    }

    static getInstanceOf(name: string): number {
        const state = store.getState().raw;
        const packages = state.metamodel;
        let instanceOf: number = 0;
        for(let ePackage of packages) {
            if(instanceOf) break;
            for(let eClass of ePackage.classes) {
                const eAttribute = eClass.attributes.filter((obj) => { return obj.name === name});
                if(eAttribute.length > 0) {instanceOf = eAttribute[0].id; break;}
            }
        }
        return instanceOf;
    }
}
