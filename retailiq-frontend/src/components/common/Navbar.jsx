import "./Navbar.css";
import {
    NotificationsNone,
    MailOutlineOutlined,
    Search,
    AccountCircle,
    Logout
} from "@mui/icons-material";

import { useNavigate } from "react-router-dom";

function Navbar() {

    const navigate = useNavigate();

    const email = localStorage.getItem("email");
    const role = localStorage.getItem("role");

    const logout = () => {

        localStorage.clear();

        navigate("/login");

    };

    return (

        <div className="navbar">

            <div className="navbar-left">

                <h2>Dashboard</h2>

            </div>

            <div className="navbar-center">

                <Search className="search-icon"/>

                <input
                    type="text"
                    placeholder="Search..."
                />

            </div>

            <div className="navbar-right">

                <NotificationsNone className="nav-icon"/>

                <MailOutlineOutlined className="nav-icon"/>

                <div className="profile">

                    <AccountCircle
                        style={{fontSize:40}}
                    />

                    <div>

                        <h4>{email}</h4>

                        <p>{role}</p>

                    </div>

                </div>

                <Logout
                    className="nav-icon"
                    style={{cursor:"pointer"}}
                    onClick={logout}
                />

            </div>

        </div>

    );

}

export default Navbar;