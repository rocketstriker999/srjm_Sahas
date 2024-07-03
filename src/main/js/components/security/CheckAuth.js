import { Navigate } from 'react-router-dom'
import { AppContext } from '../common/AppContext'

export default function CheckAuth({ allowed_roles, children }) {

    if(AppContext().currentUser.userRole){
        return allowed_roles.includes(AppContext().currentUser.userRole) ? children : <Navigate to='/forbidden' />
    }
    else{
        //ask for login
        return <Navigate to='/login' />
    }
    

    
}