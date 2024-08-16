import { Route, Routes } from "react-router-dom";
import UserDashboard from "./dashboard/Dashboard";
import CustomError from "../common/error/CustomError";
import Navbar from "../common/navbar/Navbar";
import Profile from "./Profile";
import Login from "./Login";
import Forbidden from "../common/security/Forbidden";
import HasAuthentication from "../common/security/HasAuthetication";
import HasNoAuthentication from "../common/security/HasNoAuthentication";
import HasRole from "../common/security/HasRole";
import Help from "./Help";
import Contact from "./Contact";
import { useRef } from "react";
import { Toast } from 'primereact/toast';


export default function App() {

    //const dispatch = useDispatch();

    //convert into global object
    const toast = useRef(null);

    return (
        <>
            <Toast ref={toast} />
            <Navbar />
            <Routes>
                <Route path="/" element={<UserDashboard />}></Route>

                <Route path="/profile" element={<HasAuthentication><Profile /></HasAuthentication>}></Route>
                <Route path="/help" element={<HasAuthentication><Help /></HasAuthentication>}></Route>

                <Route path="/manage-firm" element={<HasRole requiredRole={["FADMIN", "HADMIN"]} ><p>Firm Admin Management</p></HasRole>}></Route>

                <Route path="/login" element={<HasNoAuthentication><Login /></HasNoAuthentication>}></Route>
                <Route path="/contact" element={<HasNoAuthentication><Contact /></HasNoAuthentication>}></Route>

                <Route path="/forbidden" element={<Forbidden />}></Route>
                <Route path="*" element={<CustomError highlight="Invalid Page Request" />}></Route>
            </Routes>
        </>
    );

}