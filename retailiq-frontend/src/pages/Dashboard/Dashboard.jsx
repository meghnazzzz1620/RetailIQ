import { useEffect, useState } from "react";

import DashboardLayout from "../../layouts/DashboardLayout";
import KpiCard from "../../components/dashboard/KpiCard";
import TopProducts from "../../components/dashboard/TopProducts";
import RevenueChart from "../../components/charts/RevenueChart";
import InventoryChart from "../../components/charts/InventoryChart";
import SalesChart from "../../components/charts/SalesChart";
import RecentOrders from "../../components/dashboard/RecentOrders";
import LowStock from "../../components/dashboard/LowStock";
import AIAdvisor from "../../components/dashboard/AIAdvisor";
import { getDashboardStats } from "../../services/dashboardService";

import "./Dashboard.css";

function Dashboard() {

    const [summary, setSummary] = useState(null);

    useEffect(() => {

        loadDashboard();

    }, []);

    const loadDashboard = async () => {

        try {

            const data = await getDashboardStats();

            console.log("Dashboard API:", data);

            setSummary(data);

        }

        catch (error) {

            console.error(error);

        }

    };

    const formatCurrency = (value) => {

        if (value == null) return "₹0";

        return `₹${Number(value).toLocaleString("en-IN", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        })}`;

    };

    return (

        <DashboardLayout>

            <div className="dashboard">

                <KpiCard
                    title="Revenue"
                    value={formatCurrency(summary?.totalRevenue)}
                    color="#2563EB"
                />

                <KpiCard
                    title="Orders"
                    value={summary?.totalOrders ?? 0}
                    color="#16A34A"
                />

                <KpiCard
                    title="Customers"
                    value={summary?.totalCustomers ?? 0}
                    color="#F97316"
                />

                <KpiCard
                    title="Products"
                    value={summary?.totalProducts ?? 0}
                    color="#9333EA"
                />

                <KpiCard
                    title="Inventory Value"
                    value={formatCurrency(summary?.inventoryValue)}
                    color="#DC2626"
                />

                <KpiCard
                    title="Low Stock"
                    value={summary?.lowStockProducts ?? 0}
                    color="#CA8A04"
                />

            </div>

            <div className="charts">

                <RevenueChart />

                <InventoryChart />

            </div>

            <div className="charts">

                <SalesChart />

                <LowStock />

            </div>

            <div className="charts">

                <RecentOrders />

                <TopProducts />

            </div>
            <AIAdvisor />

        </DashboardLayout>

    );

}

export default Dashboard;