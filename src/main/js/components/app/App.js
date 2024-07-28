import { Route, Routes } from "react-router-dom";
import Dashboard from "../dashboard/Dashboard";
import About from "../about/About"
import CustomError from "../error/CustomError";
import Navbar from "../navbar/Navbar";
import './App.css';
import { AppContextProvider } from "../common/AppContext";
import Profile from "../profile/Profile";
import Login from "../login/Login";
import Forbidden from "../security/Forbidden";




export default function App() {

    return (
        <AppContextProvider>

            <Navbar />
            <div className="container px-8 mt-4">
                <Routes>
                    <Route path="/" element={<Dashboard />}></Route>
                    <Route path="/profile" element={<Profile />}></Route>
                    <Route path="/about" element={<About />}></Route>
                    <Route path="/login" element={<Login />}></Route>
                    <Route path="/forbidden" element={<Forbidden />}></Route>
                    <Route path="*" element={<CustomError highlight="Invalid Page Request" />}></Route>
                </Routes>
            </div>
            
        </AppContextProvider>
    )

}