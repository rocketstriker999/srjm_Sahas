import { AppContext } from '../common/AppContext'


export default function Login() {

    const appContext = AppContext();


    return <button onClick={() => { appContext.login({ "name": "user", "userRole":"user" }) }}>Login Now</button>

}