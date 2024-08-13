import { configureStore } from '@reduxjs/toolkit';
import reducerAuth from './sliceAuth';
import reducerTemplate from './sliceTemplate';


const state = configureStore({
  reducer: {
    stateAuth: reducerAuth,
    stateTemplate: reducerTemplate,

  },
});

export default state;
