import {
    ResponsiveContainer,
    BarChart,
    Bar,
    CartesianGrid,
    XAxis,
    YAxis,
    Tooltip
} from "recharts";

import { useEffect, useState } from "react";

import { getWeeklyOrders } from "../../services/dashboardService";

import "./RevenueChart.css";

function SalesChart() {

    const [data, setData] = useState([]);

    useEffect(() => {

        loadWeeklyOrders();

    }, []);

    const loadWeeklyOrders = async () => {

        try {

            const response = await getWeeklyOrders();

            setData(response);

        }

        catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="chart-card">

            <h3>Weekly Orders</h3>

            <ResponsiveContainer
                width="100%"
                height={300}
            >

                <BarChart data={data}>

                    <CartesianGrid strokeDasharray="3 3" />

                    <XAxis dataKey="day" />

                    <YAxis />

                    <Tooltip />

                    <Bar
                        dataKey="totalOrders"
                        fill="#16A34A"
                    />

                </BarChart>

            </ResponsiveContainer>

        </div>

    );

}

export default SalesChart;