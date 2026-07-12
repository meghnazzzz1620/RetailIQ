import { useEffect, useState } from "react";
import { IconButton } from "@mui/material";
import { Edit, Delete } from "@mui/icons-material";

import DashboardLayout from "../../layouts/DashboardLayout";
import PageHeader from "../../components/common/PageHeader";
import SearchBar from "../../components/common/SearchBar";
import CommonTable from "../../components/tables/CommonTable";
import SupplierDialog from "../../components/forms/SupplierDialog";

import {
    getAllSuppliers,
    getSupplierById,
    deleteSupplier
} from "../../services/supplierService";

function Suppliers() {

    const [open, setOpen] = useState(false);

    const [suppliers, setSuppliers] = useState([]);

    const [selectedSupplier, setSelectedSupplier] = useState(null);

    const [isEdit, setIsEdit] = useState(false);

    const loadSuppliers = async () => {

        try {

            const data = await getAllSuppliers();

            setSuppliers(data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadSuppliers();

    }, []);

    const handleOpen = () => {

        setSelectedSupplier(null);

        setIsEdit(false);

        setOpen(true);

    };

    const handleClose = () => {

        setOpen(false);

    };

    const handleEdit = async (id) => {

        try {

            const supplier = await getSupplierById(id);

            setSelectedSupplier(supplier);

            setIsEdit(true);

            setOpen(true);

        } catch (error) {

            console.error(error);

            alert("Unable to load supplier.");

        }

    };

    const handleDelete = async (id) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this supplier?"
        );

        if (!confirmDelete) return;

        try {

            await deleteSupplier(id);

            loadSuppliers();

        } catch (error) {

            console.error(error);

            alert("Unable to delete supplier.");

        }

    };

    const columns = [

        {
            field: "id",
            headerName: "ID",
            width: 80
        },

        {
            field: "supplierCode",
            headerName: "Supplier Code",
            flex: 1
        },

        {
            field: "companyName",
            headerName: "Company Name",
            flex: 1.5
        },

        {
            field: "contactPerson",
            headerName: "Contact Person",
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

    const rows = suppliers.map((supplier) => ({

        id: supplier.supplierId,

        supplierId: supplier.supplierId,

        supplierCode: supplier.supplierCode,

        companyName: supplier.companyName,

        contactPerson: supplier.contactPerson,

        email: supplier.email,

        phone: supplier.phone

    }));

    return (

        <DashboardLayout>

            <PageHeader
                title="Suppliers"
                subtitle="Manage Suppliers"
                buttonText="+ Add Supplier"
                onClick={handleOpen}
            />

            <SearchBar
                placeholder="Search Suppliers..."
            />

            <CommonTable
                rows={rows}
                columns={columns}
            />

            <SupplierDialog
                open={open}
                handleClose={handleClose}
                refreshSuppliers={loadSuppliers}
                selectedSupplier={selectedSupplier}
                isEdit={isEdit}
            />

        </DashboardLayout>

    );

}

export default Suppliers;