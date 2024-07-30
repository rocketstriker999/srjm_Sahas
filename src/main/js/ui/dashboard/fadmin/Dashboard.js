import React from 'react';
import { Button } from 'primereact/button';
import { useDispatch, useSelector } from 'react-redux';

import { setCurrentUser, removeCurrentUser } from '../../../redux/authSlice';

export default ()=> {

    const dispatch = useDispatch();

    const userData = useSelector((state) => state.authState.user);

    return (

        <h1>Firm Admin Dashboard</h1>

        // <Button onClick={() => {

        //     console.log(userData)

        //     if(userData){
        //         dispatch(removeCurrentUser())
        //     }else{
        //         dispatch(setCurrentUser({ name: "Nisarg", authorities: ["view_sec1","view_sec2","manage_firm","manage_some_data"] }))
        //     }
            
           
        // }} label="Submit" />
    );
}