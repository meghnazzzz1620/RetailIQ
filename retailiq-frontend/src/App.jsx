import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login/Login";

import Dashboard from "./pages/Dashboard/Dashboard";
import Products from "./pages/Products/Products";
import Categories from "./pages/Categories/Categories";
import Brands from "./pages/Brands/Brands";
import Suppliers from "./pages/Suppliers/Suppliers";
import Warehouses from "./pages/Warehouses/Warehouses";
import Inventory from "./pages/Inventory/Inventory";
import Customers from "./pages/Customers/Customers";
import Employees from "./pages/Employees/Employees";
import Orders from "./pages/Orders/Orders";

function App() {

    return (

        <BrowserRouter>

            <Routes>

                <Route path="/" element={<Login />} />

                <Route path="/login" element={<Login />} />

                <Route path="/dashboard" element={<Dashboard />} />

                <Route path="/products" element={<Products />} />

                <Route path="/categories" element={<Categories />} />

                <Route path="/brands" element={<Brands />} />

                <Route path="/suppliers" element={<Suppliers />} />

                <Route path="/warehouses" element={<Warehouses />} />

                <Route path="/inventory" element={<Inventory />} />

                <Route path="/customers" element={<Customers />} />

                <Route path="/employees" element={<Employees />} />

                <Route path="/orders" element={<Orders />} />

                
            </Routes>

        </BrowserRouter>

    );

}

export default App;