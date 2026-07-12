import {
    Dashboard,
    Inventory2,
    Category,
    LocalShipping,
    Warehouse,
    People,
    Person,
    ShoppingCart,
    BrandingWatermark
} from "@mui/icons-material";

import { NavLink } from "react-router-dom";
import "./Sidebar.css";

const role = localStorage.getItem("role");

const menuItems = [

    { icon: Dashboard, text: "Dashboard", path: "/dashboard" },

    { icon: Inventory2, text: "Products", path: "/products" },

    { icon: Category, text: "Categories", path: "/categories" },

    { icon: BrandingWatermark, text: "Brands", path: "/brands" },

    ...(role !== "EMPLOYEE"
        ? [{ icon: LocalShipping, text: "Suppliers", path: "/suppliers" }]
        : []),

    ...(role !== "EMPLOYEE"
        ? [{ icon: Warehouse, text: "Warehouses", path: "/warehouses" }]
        : []),

    { icon: Inventory2, text: "Inventory", path: "/inventory" },

    { icon: People, text: "Customers", path: "/customers" },

    ...(role === "ADMIN"
        ? [{ icon: Person, text: "Employees", path: "/employees" }]
        : []),

    { icon: ShoppingCart, text: "Orders", path: "/orders" }

];

function Sidebar() {

    return (

        <div className="sidebar">

            <div className="logo-section">

                <h2>RetailIQ</h2>

                <p>Enterprise ERP</p>

            </div>

            <div className="menu">

                {menuItems.map((item) => {

                    const Icon = item.icon;

                    return (

                        <NavLink
                            key={item.text}
                            to={item.path}
                            className={({ isActive }) =>
                                isActive
                                    ? "menu-item active"
                                    : "menu-item"
                            }
                            style={{
                                textDecoration: "none",
                                color: "inherit"
                            }}
                        >

                            <Icon />

                            <span>{item.text}</span>

                        </NavLink>

                    );

                })}

            </div>

        </div>

    );

}

export default Sidebar;