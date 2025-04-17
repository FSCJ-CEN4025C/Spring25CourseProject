import { createReducer, on } from '@ngrx/store';
import { setError, setToken } from './auth.actions';
import { jwtDecode } from 'jwt-decode';
import { User } from '../models/user.interface';



export interface AuthState {
    userDetails: User | null;
    token: string;
    error: any
}

export const initialState: AuthState = {
    userDetails: null,
    token: "",
    error: null
}

export const authReducer = createReducer(
  initialState,
  on(setToken, (state, { token }) => { 
    const decodedToken = jwtDecode(token)
    const user: User = { username: decodedToken.sub || '', email: '', password: ''}
    return {...state, token: token, userDetails:  user}
    }),
  on(setError, (state, { error }) => { return {...state, error}}),

  );
