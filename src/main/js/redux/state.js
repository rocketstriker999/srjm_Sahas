import { configureStore } from '@reduxjs/toolkit';
import authReducer from './authSlice';


const state = configureStore({
  reducer: {
    authState: authReducer,
  },
});

export default state;
