import { useEffect, useState } from "react";
import { IconButton } from "@mui/material";
import { Edit, Delete } from "@mui/icons-material";

import DashboardLayout from "../../layouts/DashboardLayout";
import PageHeader from "../../components/common/PageHeader";
import SearchBar from "../../components/common/SearchBar";
import CommonTable from "../../components/tables/CommonTable";
import WarehouseDialog from "../../components/forms/WarehouseDialog";

import {
    getAllWarehouses,
    getWarehouseById,
    deleteWarehouse
} from "../../services/warehouseService";

function Warehouses() {

    const [open, setOpen] = useState(false);

    const [warehouses, setWarehouses] = useState([]);

    const [selectedWarehouse, setSelectedWarehouse] = useState(null);

    const [isEdit, setIsEdit] = useState(false);

    const loadWarehouses = async () => {

        try {

            const data = await getAllWarehouses();

            setWarehouses(data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadWarehouses();

    }, []);

    const handleOpen = () => {

        setSelectedWarehouse(null);

        setIsEdit(false);

        setOpen(true);

    };

    const handleClose = () => {

        setOpen(false);

    };

    const handleEdit = async (id) => {

        try {

            const warehouse = await getWarehouseById(id);

            setSelectedWarehouse(warehouse);

            setIsEdit(true);

            setOpen(true);

        } catch (error) {

            console.error(error);

            alert("Unable to load warehouse.");

        }

    };

    const handleDelete = async (id) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this warehouse?"
        );

        if (!confirmDelete) return;

        try {

            await deleteWarehouse(id);

            loadWarehouses();

        } catch (error) {

            console.error(error);

            alert("Unable to delete warehouse.");

        }

    };

    const columns = [

        {
            field: "id",
            headerName: "ID",
            width: 80
        },

        {
            field: "warehouseCode",
            headerName: "Code",
            flex: 1
        },

        {
            field: "warehouseName",
            headerName: "Warehouse",
            flex: 1.5
        },

        {
            field: "managerName",
            headerName: "Manager",
            flex: 1
        },

        {
            field: "city",
            headerName: "City",
            flex: 1
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

    const rows = warehouses.map((warehouse) => ({

        id: warehouse.warehouseId,

        warehouseId: warehouse.warehouseId,

        warehouseCode: warehouse.warehouseCode,

        warehouseName: warehouse.warehouseName,

        managerName: warehouse.managerName,

        city: warehouse.city,

        phone: warehouse.phone

    }));

    return (

        <DashboardLayout>

            <PageHeader
                title="Warehouses"
                subtitle="Manage Warehouses"
                buttonText="+ Add Warehouse"
                onClick={handleOpen}
            />

            <SearchBar
                placeholder="Search Warehouses..."
            />

            <CommonTable
                rows={rows}
                columns={columns}
            />

            <WarehouseDialog
                open={open}
                handleClose={handleClose}
                refreshWarehouses={loadWarehouses}
                selectedWarehouse={selectedWarehouse}
                isEdit={isEdit}
            />

        </DashboardLayout>

    );

}

export default Warehouses;