import { createSelector, createFeatureSelector } from '@ngrx/store';
import { AppState } from 'src/app/state/app.state';
import { AuthState } from './auth.reducers';
import { User } from '../models/user.interface';


export const selectAuthState = createFeatureSelector<AuthState>('authState')

export const selectError = () => createSelector(
    selectAuthState,
    (state: AuthState) => state.error
)

export const selectUser = () =>  createSelector(
    selectAuthState, 
    (state: AuthState) => state.userDetails
)