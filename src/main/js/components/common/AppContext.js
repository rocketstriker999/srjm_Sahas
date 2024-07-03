import { createContext, useState,useContext, useEffect } from "react";

const GlobalAppContext = createContext()

export const AppContextProvider=({children})=>{

    //current user variables
    const [appUser,setAppUser] = useState(false)
    
    //expose functions from global context
    const appContextFunctions={
        currentUser : appUser,
        login:(userData)=>setAppUser(userData),
        logout:()=>setAppUser(false)
    }

    //global App context which will wrap the app
    return <GlobalAppContext.Provider value={appContextFunctions}>{children}</GlobalAppContext.Provider>

}


//this will be used by compoenensts
export const AppContext=()=>useContext(GlobalAppContext)