export interface IPackage {
    id: number;
    name: string;
    uri: string;
    userId: number;
    classes: IClass[];
}

export interface IClass {
    id: number;
    name: string;
    abstract: boolean;
    packageId: number;
    userId: number;
    attributes: IAttribute[];
}

export interface IAttribute {
    id: number;
    name: string;
    type: string;
    defaultValue: string;
    classId: string;
    userId: string;
}

export interface IObject {
    id: number;
    className: string;
    values: IValue[];
}

export interface IValue {
    id: number;
    attributeName: string;
    raw: string;
    objectId: number;
    userId: number;
}
