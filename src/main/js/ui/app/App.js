import { Route, Routes } from "react-router-dom";
import UserDashboard from "../dashboard/user/Dashboard";
import FirmAdminDashboard from "../dashboard/fadmin/Dashboard";
import CustomError from "../../components/error/CustomError";
import Navbar from "../../components/navbar/Navbar";
import './App.css';
import Profile from "../profile/Profile";
import Login from "../login/Login";
import Forbidden from "../../components/security/Forbidden";
import HasAuthentication from "../../components/security/HasAuthetication";
import HasNoAuthentication from "../../components/security/HasNoAuthentication";
import HasRole from "../../components/security/HasRole";
import Help from "../help/Help";
import Contact from "../contact/Contact";

export default function App() {

    return (
        <>
            <Navbar />
            <div className="container">
                <Routes>
                    <Route path="/" element={<UserDashboard />}></Route>

                    <Route path="/profile" element={<HasAuthentication><Profile /></HasAuthentication>}></Route>
                    <Route path="/help" element={<HasAuthentication><Help /></HasAuthentication>}></Route>


                    <Route path="/manage-firm" element={<HasRole requiredRole={["FADMIN","HADMIN"]} ><FirmAdminDashboard /></HasRole>}></Route>

                    <Route path="/login" element={<HasNoAuthentication><Login /></HasNoAuthentication>}></Route>
                    <Route path="/contact" element={<HasNoAuthentication><Contact /></HasNoAuthentication>}></Route>


                    <Route path="/forbidden" element={<Forbidden />}></Route>
                    <Route path="*" element={<CustomError highlight="Invalid Page Request" />}></Route>
                </Routes>
            </div>

        </>

    )

}