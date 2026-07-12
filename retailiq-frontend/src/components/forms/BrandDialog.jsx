import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    TextField,
    Button
} from "@mui/material";

import { useState, useEffect } from "react";

import {
    createBrand,
    updateBrand
} from "../../services/brandService";

function BrandDialog({
    open,
    handleClose,
    refreshBrands,
    selectedBrand,
    isEdit
}) {

    const initialState = {

        brandName: "",

        description: ""

    };

    const [brand, setBrand] = useState(initialState);

    useEffect(() => {

        if (isEdit && selectedBrand) {

            setBrand({

                brandName: selectedBrand.brandName || "",

                description: selectedBrand.description || ""

            });

        } else {

            setBrand(initialState);

        }

    }, [selectedBrand, isEdit, open]);

    const handleChange = (e) => {

        setBrand({

            ...brand,

            [e.target.name]: e.target.value

        });

    };

    const handleSave = async () => {

        try {

            if (isEdit) {

                await updateBrand(
                    selectedBrand.brandId,
                    brand
                );

            } else {

                await createBrand(brand);

            }

            refreshBrands();

            setBrand(initialState);

            handleClose();

        } catch (error) {

            console.error(error);

            alert(
                error.response?.data?.message ||
                "Unable to save brand."
            );

        }

    };

    return (

        <Dialog
            open={open}
            onClose={handleClose}
            fullWidth
            maxWidth="sm"
        >

            <DialogTitle>

                {isEdit ? "Edit Brand" : "Add Brand"}

            </DialogTitle>

            <DialogContent>

                <TextField
                    fullWidth
                    sx={{ mt: 2 }}
                    label="Brand Name"
                    name="brandName"
                    value={brand.brandName}
                    onChange={handleChange}
                />

                <TextField
                    fullWidth
                    multiline
                    rows={3}
                    sx={{ mt: 2 }}
                    label="Description"
                    name="description"
                    value={brand.description}
                    onChange={handleChange}
                />

            </DialogContent>

            <DialogActions>

                <Button onClick={handleClose}>

                    Cancel

                </Button>

                <Button
                    variant="contained"
                    onClick={handleSave}
                >

                    {isEdit ? "Update Brand" : "Save Brand"}

                </Button>

            </DialogActions>

        </Dialog>

    );

}

export default BrandDialog;