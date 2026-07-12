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
    createProduct,
    updateProduct
} from "../../services/productService";

function ProductDialog({
    open,
    handleClose,
    refreshProducts,
    selectedProduct,
    isEdit
}) {

    const initialState = {
        sku: "",
        barcode: "",
        productName: "",
        description: "",
        purchasePrice: "",
        sellingPrice: "",
        gstPercentage: "",
        reorderLevel: "",
        safetyStock: ""
    };

    const [product, setProduct] = useState(initialState);

    useEffect(() => {

        if (isEdit && selectedProduct) {

            setProduct({

                sku: selectedProduct.sku || "",

                barcode: selectedProduct.barcode || "",

                productName: selectedProduct.productName || "",

                description: selectedProduct.description || "",

                purchasePrice: selectedProduct.purchasePrice || "",

                sellingPrice: selectedProduct.sellingPrice || "",

                gstPercentage: selectedProduct.gstPercentage || "",

                reorderLevel: selectedProduct.reorderLevel || "",

                safetyStock: selectedProduct.safetyStock || ""

            });

        } else {

            setProduct(initialState);

        }

    }, [selectedProduct, isEdit, open]);

    const handleChange = (e) => {

        setProduct({

            ...product,

            [e.target.name]: e.target.value

        });

    };

    const handleSave = async () => {

        try {

            const payload = {

                sku: product.sku,

                barcode: product.barcode,

                productName: product.productName,

                description: product.description,

                purchasePrice: Number(product.purchasePrice),

                sellingPrice: Number(product.sellingPrice),

                gstPercentage: Number(product.gstPercentage),

                reorderLevel: Number(product.reorderLevel),

                safetyStock: Number(product.safetyStock)

            };

            if (isEdit) {

                await updateProduct(
                    selectedProduct.productId,
                    payload
                );

            } else {

                await createProduct(payload);

            }

            refreshProducts();

            setProduct(initialState);

            handleClose();

        } catch (error) {

            console.error(error);

            alert(
                error.response?.data?.message ||
                "Operation failed."
            );

        }

    };

    return (

        <Dialog
            open={open}
            onClose={handleClose}
            fullWidth
            maxWidth="md"
        >

            <DialogTitle>

                {isEdit ? "Edit Product" : "Add Product"}

            </DialogTitle>

            <DialogContent>

                <Grid
                    container
                    spacing={2}
                    sx={{ mt: 1 }}
                >

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField
                            fullWidth
                            label="Product Name"
                            name="productName"
                            value={product.productName}
                            onChange={handleChange}
                        />
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField
                            fullWidth
                            label="SKU"
                            name="sku"
                            value={product.sku}
                            onChange={handleChange}
                        />
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField
                            fullWidth
                            label="Barcode"
                            name="barcode"
                            value={product.barcode}
                            onChange={handleChange}
                        />
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField
                            fullWidth
                            type="number"
                            label="Purchase Price"
                            name="purchasePrice"
                            value={product.purchasePrice}
                            onChange={handleChange}
                        />
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField
                            fullWidth
                            type="number"
                            label="Selling Price"
                            name="sellingPrice"
                            value={product.sellingPrice}
                            onChange={handleChange}
                        />
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField
                            fullWidth
                            type="number"
                            label="GST Percentage"
                            name="gstPercentage"
                            value={product.gstPercentage}
                            onChange={handleChange}
                        />
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField
                            fullWidth
                            type="number"
                            label="Reorder Level"
                            name="reorderLevel"
                            value={product.reorderLevel}
                            onChange={handleChange}
                        />
                    </Grid>

                    <Grid size={{ xs: 12, md: 6 }}>
                        <TextField
                            fullWidth
                            type="number"
                            label="Safety Stock"
                            name="safetyStock"
                            value={product.safetyStock}
                            onChange={handleChange}
                        />
                    </Grid>

                    <Grid size={{ xs: 12 }}>
                        <TextField
                            fullWidth
                            multiline
                            rows={3}
                            label="Description"
                            name="description"
                            value={product.description}
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
                    {isEdit ? "Update Product" : "Save Product"}
                </Button>

            </DialogActions>

        </Dialog>

    );

}

export default ProductDialog;