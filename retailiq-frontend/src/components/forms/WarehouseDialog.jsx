import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    TextField,
    Button,
    Grid
} from "@mui/material";

import { useState, useEffect } from "react";

import {
    createWarehouse,
    updateWarehouse
} from "../../services/warehouseService";

function WarehouseDialog({
    open,
    handleClose,
    refreshWarehouses,
    selectedWarehouse,
    isEdit
}) {

    const initialState = {

        warehouseCode: "",
        warehouseName: "",
        managerName: "",
        phone: "",
        email: "",
        address: "",
        city: "",
        state: "",
        country: "",
        postalCode: ""

    };

    const [warehouse, setWarehouse] = useState(initialState);

    useEffect(() => {

        if (isEdit && selectedWarehouse) {

            setWarehouse({

                warehouseCode: selectedWarehouse.warehouseCode || "",
                warehouseName: selectedWarehouse.warehouseName || "",
                managerName: selectedWarehouse.managerName || "",
                phone: selectedWarehouse.phone || "",
                email: selectedWarehouse.email || "",
                address: selectedWarehouse.address || "",
                city: selectedWarehouse.city || "",
                state: selectedWarehouse.state || "",
                country: selectedWarehouse.country || "",
                postalCode: selectedWarehouse.postalCode || ""

            });

        } else {

            setWarehouse(initialState);

        }

    }, [selectedWarehouse, isEdit, open]);

    const handleChange = (e) => {

        setWarehouse({

            ...warehouse,

            [e.target.name]: e.target.value

        });

    };

    const handleSave = async () => {

        try {

            if (isEdit) {

                await updateWarehouse(
                    selectedWarehouse.warehouseId,
                    warehouse
                );

            } else {

                await createWarehouse(warehouse);

            }

            refreshWarehouses();

            handleClose();

            setWarehouse(initialState);

        }

        catch (error) {

            console.error(error);

            alert(
                error.response?.data?.message ||
                "Unable to save warehouse."
            );

        }

    };

    return (

        <Dialog
            open={open}
            fullWidth
            maxWidth="md"
        >

            <DialogTitle>

                {isEdit ? "Edit Warehouse" : "Add Warehouse"}

            </DialogTitle>

            <DialogContent>

                <Grid container spacing={2} sx={{ mt: 1 }}>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField fullWidth label="Warehouse Code" name="warehouseCode" value={warehouse.warehouseCode} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField fullWidth label="Warehouse Name" name="warehouseName" value={warehouse.warehouseName} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField fullWidth label="Manager Name" name="managerName" value={warehouse.managerName} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField fullWidth label="Phone" name="phone" value={warehouse.phone} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField fullWidth label="Email" name="email" value={warehouse.email} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs: 12 }}>
                        <TextField fullWidth label="Address" name="address" value={warehouse.address} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs: 12, md: 4 }}>
                        <TextField fullWidth label="City" name="city" value={warehouse.city} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs: 12, md: 4 }}>
                        <TextField fullWidth label="State" name="state" value={warehouse.state} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs: 12, md: 4 }}>
                        <TextField fullWidth label="Country" name="country" value={warehouse.country} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField fullWidth label="Postal Code" name="postalCode" value={warehouse.postalCode} onChange={handleChange}/>
                    </Grid>

                </Grid>

            </DialogContent>

            <DialogActions>

                <Button onClick={handleClose}>
                    Cancel
                </Button>

                <Button
                    variant="contained"
                    onClick={handleSave}
                >

                    {isEdit ? "Update Warehouse" : "Save Warehouse"}

                </Button>

            </DialogActions>

        </Dialog>

    );

}

export default WarehouseDialog;