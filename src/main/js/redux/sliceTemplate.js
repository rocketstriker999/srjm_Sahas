import { createSlice } from '@reduxjs/toolkit';

export const sliceTemplate = createSlice({
  name: 'sliceTemplate',
  initialState: {
    templateDetails: false,
  },
  reducers: {
    setTemplateData: (state, action) => {
      state.templateDetails = action.payload;
    },
  },
});

export const { setTemplateData } = sliceTemplate.actions;

export default sliceTemplate.reducer;
