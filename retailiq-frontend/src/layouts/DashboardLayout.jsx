import Sidebar from "../components/common/Sidebar";
import Navbar from "../components/common/Navbar";
import "./DashboardLayout.css";

function DashboardLayout({ children }) {

    return (

        <div className="layout">

            <Sidebar/>

            <div className="main-content">

                <Navbar/>

                <div className="page-content">

                    {children}

                </div>

            </div>

        </div>

    );

}

export default DashboardLayout;