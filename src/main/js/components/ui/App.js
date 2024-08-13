import { Route, Routes } from "react-router-dom";
import UserDashboard from "./dashboard/user/Dashboard";
import FirmAdminDashboard from "./dashboard/fadmin/Dashboard";
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
import { requestHelper } from "../../utils/utils"
import { useEffect, useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setTemplateData } from '../../redux/sliceTemplate';
import { Toast } from 'primereact/toast';


export default function App() {

    const dispatch = useDispatch();

    const toast = useRef(null);


    useEffect(() => {

        requestHelper.requestServer({
            requestPath: "template/details"
        }).then(response => response.json()).then(jsonResponse => {
            dispatch(setTemplateData(jsonResponse));
        }).catch((e)=>{
            toast.current.show({ severity: 'error', summary: 'Error', detail: "Couldn't Load Template Details"});
        });

    }, []);
    return (
        <>
            <Toast ref={toast} />
            <Navbar />
            <Routes>
                <Route path="/" element={<UserDashboard />}></Route>

                <Route path="/profile" element={<HasAuthentication><Profile /></HasAuthentication>}></Route>
                <Route path="/help" element={<HasAuthentication><Help /></HasAuthentication>}></Route>

                <Route path="/manage-firm" element={<HasRole requiredRole={["FADMIN", "HADMIN"]} ><FirmAdminDashboard /></HasRole>}></Route>

                <Route path="/login" element={<HasNoAuthentication><Login /></HasNoAuthentication>}></Route>
                <Route path="/contact" element={<HasNoAuthentication><Contact /></HasNoAuthentication>}></Route>

                <Route path="/forbidden" element={<Forbidden />}></Route>
                <Route path="*" element={<CustomError highlight="Invalid Page Request" />}></Route>
            </Routes>

        </>

    )

}