import { useEffect, useState } from "react";
import { IconButton } from "@mui/material";
import { Edit, Delete } from "@mui/icons-material";

import DashboardLayout from "../../layouts/DashboardLayout";
import PageHeader from "../../components/common/PageHeader";
import SearchBar from "../../components/common/SearchBar";
import CommonTable from "../../components/tables/CommonTable";
import CustomerDialog from "../../components/forms/CustomerDialog";

import {
    getAllCustomers,
    getCustomerById,
    deleteCustomer
} from "../../services/customerService";

function Customers() {

    const [open, setOpen] = useState(false);

    const [customers, setCustomers] = useState([]);

    const [selectedCustomer, setSelectedCustomer] = useState(null);

    const [isEdit, setIsEdit] = useState(false);

    const loadCustomers = async () => {

        try {

            const data = await getAllCustomers();

            setCustomers(data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadCustomers();

    }, []);

    const handleOpen = () => {

        setSelectedCustomer(null);

        setIsEdit(false);

        setOpen(true);

    };

    const handleClose = () => {

        setOpen(false);

    };

    const handleEdit = async (id) => {

        try {

            const customer = await getCustomerById(id);

            setSelectedCustomer(customer);

            setIsEdit(true);

            setOpen(true);

        } catch (error) {

            console.error(error);

            alert("Unable to load customer.");

        }

    };

    const handleDelete = async (id) => {

        if (!window.confirm("Are you sure you want to delete this customer?")) {

            return;

        }

        try {

            await deleteCustomer(id);

            loadCustomers();

        } catch (error) {

            console.error(error);

            alert("Unable to delete customer.");

        }

    };

    const columns = [

        {
            field: "id",
            headerName: "ID",
            width: 80
        },

        {
            field: "firstName",
            headerName: "First Name",
            flex: 1
        },

        {
            field: "lastName",
            headerName: "Last Name",
            flex: 1
        },

        {
            field: "email",
            headerName: "Email",
            flex: 1.5
        },

        {
            field: "phone",
            headerName: "Phone",
            flex: 1
        },

        {
            field: "city",
            headerName: "City",
            flex: 1
        },

        {
            field: "actions",
            headerName: "Actions",
            width: 140,
            sortable: false,

            renderCell: (params) => (

                <>

                    <IconButton
                        color="primary"
                        onClick={() => handleEdit(params.row.id)}
                    >

                        <Edit />

                    </IconButton>

                    <IconButton
                        color="error"
                        onClick={() => handleDelete(params.row.id)}
                    >

                        <Delete />

                    </IconButton>

                </>

            )

        }

    ];

    const rows = customers.map(customer => ({

        id: customer.customerId,

        customerId: customer.customerId,

        firstName: customer.firstName,

        lastName: customer.lastName,

        email: customer.email,

        phone: customer.phone,

        city: customer.city

    }));

    return (

        <DashboardLayout>

            <PageHeader
                title="Customers"
                subtitle="Manage Customers"
                buttonText="+ Add Customer"
                onClick={handleOpen}
            />

            <SearchBar
                placeholder="Search Customers..."
            />

            <CommonTable
                rows={rows}
                columns={columns}
            />

            <CustomerDialog
                open={open}
                handleClose={handleClose}
                refreshCustomers={loadCustomers}
                selectedCustomer={selectedCustomer}
                isEdit={isEdit}
            />

        </DashboardLayout>

    );

}

export default Customers;