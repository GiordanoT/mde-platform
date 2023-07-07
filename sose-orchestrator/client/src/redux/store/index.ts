import {createSlice, PayloadAction} from '@reduxjs/toolkit';
import {IObject, IPackage} from "../../model/me/interfaces";

type T = {loading: boolean, userId: number, selected: string, metamodel: IPackage[], model: IObject[]};
const initialState: T = {loading: false, userId: 0, selected: '', metamodel: [], model: []};
export const slice = createSlice({
    name: 'raw',
    initialState,
    reducers: {
        setLoading(state: T, action: PayloadAction<boolean>){state.loading = action.payload},
        setUserId(state: T, action: PayloadAction<number>){state.userId = action.payload},
        setSelected(state: T, action: PayloadAction<string>){state.selected = action.payload},
        setMetamodel(state: T, action: PayloadAction<any>){state.metamodel = action.payload},
        setModel(state: T, action: PayloadAction<any>){state.model = action.payload}
    }
});

export const reducer = slice.reducer;
