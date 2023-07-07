import {combineReducers} from 'redux';
import {configureStore} from '@reduxjs/toolkit';
import {reducer} from './store';

export const rootReducer = combineReducers({
    raw: reducer
});
export type RootState = ReturnType<typeof rootReducer>;
export const store = configureStore({reducer: rootReducer});
