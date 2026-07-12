import {
    ResponsiveContainer,
    PieChart,
    Pie,
    Cell,
    Tooltip,
    Legend
} from "recharts";

import { useEffect, useState } from "react";

import { getWarehouseInventory } from "../../services/dashboardService";

import "./RevenueChart.css";

const COLORS = [
    "#2563EB",
    "#16A34A",
    "#F97316",
    "#9333EA",
    "#DC2626",
    "#0891B2"
];

function InventoryChart() {

    const [data, setData] = useState([]);

    useEffect(() => {

        loadInventory();

    }, []);

    const loadInventory = async () => {

        try {

            const response = await getWarehouseInventory();

            setData(response);

        }

        catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="chart-card">

            <h3>Inventory by Warehouse</h3>

            <ResponsiveContainer
                width="100%"
                height={300}
            >

                <PieChart>

                    <Pie
                        data={data}
                        dataKey="totalQuantity"
                        nameKey="warehouseName"
                        outerRadius={100}
                        label
                    >

                        {
                            data.map((entry, index) => (

                                <Cell
                                    key={index}
                                    fill={
                                        COLORS[
                                            index % COLORS.length
                                        ]
                                    }
                                />

                            ))
                        }

                    </Pie>

                    <Tooltip />

                    <Legend />

                </PieChart>

            </ResponsiveContainer>

        </div>

    );

}

export default InventoryChart;