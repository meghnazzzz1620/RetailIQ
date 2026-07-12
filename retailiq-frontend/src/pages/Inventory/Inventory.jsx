import { useEffect, useState } from "react";
import { IconButton } from "@mui/material";
import { Edit, Delete } from "@mui/icons-material";

import DashboardLayout from "../../layouts/DashboardLayout";
import PageHeader from "../../components/common/PageHeader";
import SearchBar from "../../components/common/SearchBar";
import CommonTable from "../../components/tables/CommonTable";
import InventoryDialog from "../../components/forms/InventoryDialog";

import {
    getAllInventory,
    getInventoryById,
    deleteInventory
} from "../../services/inventoryService";

function Inventory() {

    const [open, setOpen] = useState(false);

    const [inventory, setInventory] = useState([]);

    const [selectedInventory, setSelectedInventory] = useState(null);

    const [isEdit, setIsEdit] = useState(false);

    const loadInventory = async () => {

        try {

            const data = await getAllInventory();

            setInventory(data);

        }

        catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadInventory();

    }, []);

    const handleOpen = () => {

        setSelectedInventory(null);

        setIsEdit(false);

        setOpen(true);

    };

    const handleClose = () => {

        setOpen(false);

    };

    const handleEdit = async (id) => {

        try {

            const data = await getInventoryById(id);

            setSelectedInventory(data);

            setIsEdit(true);

            setOpen(true);

        }

        catch (error) {

            console.error(error);

            alert("Unable to load inventory.");

        }

    };

    const handleDelete = async (id) => {

        if (!window.confirm("Are you sure you want to delete this inventory?")) {

            return;

        }

        try {

            await deleteInventory(id);

            loadInventory();

        }

        catch (error) {

            console.error(error);

            alert("Unable to delete inventory.");

        }

    };

    const columns = [

        {
            field: "id",
            headerName: "ID",
            width: 80
        },

        {
            field: "productName",
            headerName: "Product",
            flex: 1.3
        },

        {
            field: "warehouseName",
            headerName: "Warehouse",
            flex: 1.3
        },

        {
            field: "availableQuantity",
            headerName: "Available",
            flex: 1
        },

        {
            field: "reservedQuantity",
            headerName: "Reserved",
            flex: 1
        },

        {
            field: "reorderLevel",
            headerName: "Reorder",
            flex: 1
        },

        {
            field: "maximumCapacity",
            headerName: "Capacity",
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

    const rows = inventory.map(item => ({

        id: item.inventoryId,

        inventoryId: item.inventoryId,

        productName: item.productName,

        warehouseName: item.warehouseName,

        availableQuantity: item.availableQuantity,

        reservedQuantity: item.reservedQuantity,

        reorderLevel: item.reorderLevel,

        maximumCapacity: item.maximumCapacity

    }));

    return (

        <DashboardLayout>

            <PageHeader
                title="Inventory"
                subtitle="Manage Inventory"
                buttonText="+ Add Inventory"
                onClick={handleOpen}
            />

            <SearchBar
                placeholder="Search Inventory..."
            />

            <CommonTable
                rows={rows}
                columns={columns}
            />

            <InventoryDialog
                open={open}
                handleClose={handleClose}
                refreshInventory={loadInventory}
                selectedInventory={selectedInventory}
                isEdit={isEdit}
            />

        </DashboardLayout>

    );

}

export default Inventory;