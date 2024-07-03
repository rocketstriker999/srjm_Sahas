import { AppContext } from "../common/AppContext"
import CheckAuth from "../security/CheckAuth"

export default function Profile() {
    return <CheckAuth allowed_roles={['admin']}>
        <h1>This is profile section {JSON.stringify(AppContext().currentUser)}</h1>
    </CheckAuth>
}