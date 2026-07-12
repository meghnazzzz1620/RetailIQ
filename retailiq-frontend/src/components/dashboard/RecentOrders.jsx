import "./RecentOrders.css";

import { useEffect, useState } from "react";

import { getRecentOrders } from "../../services/dashboardService";

function RecentOrders() {

    const [orders, setOrders] = useState([]);

    useEffect(() => {

        loadRecentOrders();

    }, []);

    const loadRecentOrders = async () => {

        try {

            const data = await getRecentOrders();

            setOrders(data);

        }

        catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="table-card">

            <h3>

                Recent Orders

            </h3>

            <table>

                <thead>

                <tr>

                    <th>Order No</th>

                    <th>Customer</th>

                    <th>Total</th>

                    <th>Status</th>

                </tr>

                </thead>

                <tbody>

                {

                    orders.map((order) => (

                        <tr key={order.orderId}>

                            <td>{order.orderNumber}</td>

                            <td>{order.customerName}</td>

                            <td>₹{order.grandTotal}</td>

                            <td>{order.orderStatus}</td>

                        </tr>

                    ))

                }

                </tbody>

            </table>

        </div>

    );

}

export default RecentOrders;