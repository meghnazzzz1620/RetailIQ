import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    Tooltip,
    CartesianGrid,
    ResponsiveContainer
} from "recharts";

import { useEffect, useState } from "react";

import { getMonthlySales } from "../../services/dashboardService";

import "./RevenueChart.css";

function RevenueChart() {

    const [data, setData] = useState([]);

    useEffect(() => {

        loadChart();

    }, []);

    const loadChart = async () => {

        try {

            const response = await getMonthlySales();

            setData(response);

        }

        catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="chart-card">

            <h3>Monthly Revenue</h3>

            <ResponsiveContainer
                width="100%"
                height={300}
            >

                <LineChart data={data}>

                    <CartesianGrid strokeDasharray="3 3" />

                    <XAxis
                        dataKey="month"
                    />

                    <YAxis
                        tickFormatter={(value) =>
                            `₹${(value / 10000000).toFixed(1)} Cr`
                        }
                    />

                    <Tooltip
                        formatter={(value) => [
                            `₹${Number(value).toLocaleString("en-IN", {
                                minimumFractionDigits: 2,
                                maximumFractionDigits: 2
                            })}`,
                            "Revenue"
                        ]}
                    />

                    <Line
                        type="monotone"
                        dataKey="revenue"
                        stroke="#2563EB"
                        strokeWidth={3}
                        dot={{ r: 4 }}
                        activeDot={{ r: 7 }}
                    />

                </LineChart>

            </ResponsiveContainer>

        </div>

    );

}

export default RevenueChart;