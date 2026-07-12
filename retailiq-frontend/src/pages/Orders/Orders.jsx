import { useEffect, useState } from "react";

import DashboardLayout from "../../layouts/DashboardLayout";
import PageHeader from "../../components/common/PageHeader";
import CommonTable from "../../components/tables/CommonTable";
import OrderDialog from "../../components/forms/OrderDialog";

import { getAllOrders } from "../../services/orderService";

function Orders() {

    const [orders, setOrders] = useState([]);

    const [open, setOpen] = useState(false);

    const loadOrders = async () => {

        try {

            const data = await getAllOrders();

            setOrders(data);

        }

        catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadOrders();

    }, []);

    const columns = [

        { field: "id", headerName: "ID", width: 90 },

        { field: "orderNumber", headerName: "Order No", flex: 1 },

        { field: "customer", headerName: "Customer", flex: 1 },

        { field: "employee", headerName: "Employee", flex: 1 },

        { field: "grandTotal", headerName: "Total", flex: 1 },

        { field: "status", headerName: "Status", flex: 1 }

    ];

    const rows = orders.map(order => ({

        id: order.orderId,

        orderNumber: order.orderNumber,

        customer: order.customerName,

        employee: order.employeeName,

        grandTotal: order.grandTotal,

        status: order.orderStatus

    }));

    return (

        <DashboardLayout>

            <PageHeader

                title="Orders"

                subtitle="Manage Orders"

                buttonText="+ Create Order"

                onClick={() => setOpen(true)}

            />

            <CommonTable

                rows={rows}

                columns={columns}

            />

            <OrderDialog

                open={open}

                handleClose={() => setOpen(false)}

                refreshOrders={loadOrders}

            />

        </DashboardLayout>

    );

}

export default Orders;