import {Json} from "../types";

export class DPackage {
    name: string;
    uri: string;
    userId: number;

    constructor(name: string, uri: string, userId: number) {
        this.name = name;
        this.uri = uri;
        this.userId = userId;
    }

    json(): Json {
        return {
            name: this.name,
            uri: this.uri,
            userId: this.userId
        };
    }

}

export class DClass {
    name: string;
    abstract: boolean;
    packageId: number;
    userId: number;

    constructor(name: string, abstract: boolean, packageId: number, userId: number) {
        this.name = name;
        this.abstract = abstract;
        this.packageId = packageId;
        this.userId = userId;
    }

    json(): Json {
        return {
            name: this.name,
            isAbstract: !!this.abstract,
            packageId: this.packageId,
            userId: this.userId
        }
    }
}

export class DAttribute {
    name: string;
    type: string;
    defaultValue: string;
    classId: number;
    userId: number;

    constructor(name: string, type: string, defaultValue: string, classId: number, userId: number) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
        this.classId = classId;
        this.userId = userId;
    }

    json(): Json {
        return {
            name: this.name,
            type: this.type,
            defaultValue: this.defaultValue,
            classId: this.classId,
            userId: this.userId
        }
    }
}

export class DObject {
    instanceOf: number;
    userId: number;

    constructor(instanceOf: number, userId: number) {
        this.instanceOf = instanceOf;
        this.userId = userId;
    }

    json(): Json {
        return {
            instanceOf: this.instanceOf,
            userId: this.userId
        }
    }
}

export class DValue {
    instanceOf: number;
    raw: string;
    objectId: number;
    userId: number;

    constructor(instanceOf: number, raw: string, objectId: number, userId: number) {
        this.instanceOf = instanceOf;
        this.raw = raw;
        this.objectId = objectId;
        this.userId = userId;
    }

    json(): Json {
        return {
            instanceOf: this.instanceOf,
            raw: this.raw,
            objectId: this.objectId,
            userId: this.userId
        }
    }
}
