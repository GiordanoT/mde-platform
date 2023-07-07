import {Json} from "./types";

interface HelperCrud {crud: string, isMetamodel: boolean}
export default class U {
    static getId(me: string, id: number): string {
        return me + '.' + id;
    }
    static getIdentifier() {
        return Math.floor(Math.random() * 999);
    }
    static getCrudClassname(classname: string): HelperCrud {
        switch (classname) {
            case 'EMetamodel':
                return {crud: 'metamodel', isMetamodel: true};
            case 'EPackage':
                return {crud: 'package', isMetamodel: true};
            case 'EClass':
                return {crud: 'class', isMetamodel: true};
            case 'EAttribute':
                return {crud: 'attribute', isMetamodel: true};
            case 'EModel':
                return {crud: 'model', isMetamodel: false};
            case 'EObject':
                return {crud: 'object', isMetamodel: false};
            case 'EValue':
                return {crud: 'value', isMetamodel: false};
            default:
                return {crud: '', isMetamodel: false};
        }
    }

    static removeFields(obj: any, list: string[]) {
        if (obj && typeof obj === 'object') {
            for (let item in obj) {
                if (list.includes(item)) {
                    delete obj[item];
                } else {
                    if (Array.isArray(obj[item])) {
                        for (let el of obj[item]) {
                            U.removeFields(el, list);
                        }
                    } else {
                        U.removeFields(obj[item], list);
                    }
                }
            }
        }
        return obj;
    }
}
