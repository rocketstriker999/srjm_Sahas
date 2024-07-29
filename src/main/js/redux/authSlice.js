import { createSlice } from '@reduxjs/toolkit';

export const authSlice = createSlice({
  name: 'authSlice',
  initialState: {
    user: false,
  },
  reducers: {
    removeCurrentUser: (state) => {
      state.user = false;
    },
    setCurrentUser: (state, action) => {
      state.user = action.payload;
    },
  },
});

export const { setCurrentUser, removeCurrentUser} = authSlice.actions;

export default authSlice.reducer;
