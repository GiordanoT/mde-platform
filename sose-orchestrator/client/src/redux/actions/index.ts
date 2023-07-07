import {store} from '../index';
import {slice} from '../store';

export class ReduxAction {
    static setLoading(value: boolean): void {
        store.dispatch(slice.actions.setLoading(value));
    }

    static setUserId(value: number): void {
        store.dispatch(slice.actions.setUserId(value));
    }

    static setSelected(value: string): void {
        store.dispatch(slice.actions.setSelected(value));
    }

    static setMetamodel(value: any): void {
        store.dispatch(slice.actions.setMetamodel(value));
    }

    static setModel(value: any): void {
        store.dispatch(slice.actions.setModel(value));
    }
}

