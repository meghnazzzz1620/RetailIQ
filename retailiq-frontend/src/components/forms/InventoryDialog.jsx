import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button,
    Grid,
    TextField,
    MenuItem
} from "@mui/material";

import { useState, useEffect } from "react";

import {
    createInventory,
    updateInventory
} from "../../services/inventoryService";

import { getAllProducts } from "../../services/productService";
import { getAllWarehouses } from "../../services/warehouseService";

function InventoryDialog({
    open,
    handleClose,
    refreshInventory,
    selectedInventory,
    isEdit
}) {

    const initialState = {

        productId: "",
        warehouseId: "",
        availableQuantity: "",
        reservedQuantity: "",
        reorderLevel: "",
        maximumCapacity: ""

    };

    const [inventory, setInventory] = useState(initialState);

    const [products, setProducts] = useState([]);

    const [warehouses, setWarehouses] = useState([]);

    useEffect(() => {

        loadDropdowns();

    }, []);

    useEffect(() => {

        if (isEdit && selectedInventory) {

            setInventory({

                productId: selectedInventory.productId,
                warehouseId: selectedInventory.warehouseId,
                availableQuantity: selectedInventory.availableQuantity,
                reservedQuantity: selectedInventory.reservedQuantity,
                reorderLevel: selectedInventory.reorderLevel,
                maximumCapacity: selectedInventory.maximumCapacity

            });

        }

        else {

            setInventory(initialState);

        }

    }, [selectedInventory, isEdit, open]);

    const loadDropdowns = async () => {

        try {

            const productData = await getAllProducts();

            const warehouseData = await getAllWarehouses();

            setProducts(productData);

            setWarehouses(warehouseData);

        }

        catch (error) {

            console.error(error);

        }

    };

    const handleChange = (e) => {

        setInventory({

            ...inventory,

            [e.target.name]: e.target.value

        });

    };

    const handleSave = async () => {

        try {

            const payload = {

                productId: Number(inventory.productId),

                warehouseId: Number(inventory.warehouseId),

                availableQuantity: Number(inventory.availableQuantity),

                reservedQuantity: Number(inventory.reservedQuantity),

                reorderLevel: Number(inventory.reorderLevel),

                maximumCapacity: Number(inventory.maximumCapacity)

            };

            if (isEdit) {

                await updateInventory(
                    selectedInventory.inventoryId,
                    payload
                );

            }

            else {

                await createInventory(payload);

            }

            refreshInventory();

            handleClose();

            setInventory(initialState);

        }

        catch (error) {

            console.error(error);

            alert(
                error.response?.data?.message ||
                "Unable to save inventory."
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

                {isEdit ? "Edit Inventory" : "Add Inventory"}

            </DialogTitle>

            <DialogContent>

                <Grid container spacing={2} sx={{ mt: 1 }}>

                    <Grid size={{ xs:12, md:6 }}>

                        <TextField
                            select
                            fullWidth
                            label="Product"
                            name="productId"
                            value={inventory.productId}
                            onChange={handleChange}
                        >

                            {products.map(product => (

                                <MenuItem
                                    key={product.productId}
                                    value={product.productId}
                                >

                                    {product.productName}

                                </MenuItem>

                            ))}

                        </TextField>

                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>

                        <TextField
                            select
                            fullWidth
                            label="Warehouse"
                            name="warehouseId"
                            value={inventory.warehouseId}
                            onChange={handleChange}
                        >

                            {warehouses.map(warehouse => (

                                <MenuItem
                                    key={warehouse.warehouseId}
                                    value={warehouse.warehouseId}
                                >

                                    {warehouse.warehouseName}

                                </MenuItem>

                            ))}

                        </TextField>

                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>

                        <TextField
                            fullWidth
                            type="number"
                            label="Available Quantity"
                            name="availableQuantity"
                            value={inventory.availableQuantity}
                            onChange={handleChange}
                        />

                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>

                        <TextField
                            fullWidth
                            type="number"
                            label="Reserved Quantity"
                            name="reservedQuantity"
                            value={inventory.reservedQuantity}
                            onChange={handleChange}
                        />

                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>

                        <TextField
                            fullWidth
                            type="number"
                            label="Reorder Level"
                            name="reorderLevel"
                            value={inventory.reorderLevel}
                            onChange={handleChange}
                        />

                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>

                        <TextField
                            fullWidth
                            type="number"
                            label="Maximum Capacity"
                            name="maximumCapacity"
                            value={inventory.maximumCapacity}
                            onChange={handleChange}
                        />

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

                    {isEdit ? "Update Inventory" : "Save Inventory"}

                </Button>

            </DialogActions>

        </Dialog>

    );

}

export default InventoryDialog;