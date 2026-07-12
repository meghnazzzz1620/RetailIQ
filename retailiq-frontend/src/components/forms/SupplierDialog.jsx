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
    createSupplier,
    updateSupplier
} from "../../services/supplierService";

function SupplierDialog({
    open,
    handleClose,
    refreshSuppliers,
    selectedSupplier,
    isEdit
}) {

    const initialState = {

        supplierCode: "",
        companyName: "",
        gstNumber: "",
        contactPerson: "",
        email: "",
        phone: "",
        street: "",
        city: "",
        state: "",
        country: "",
        postalCode: ""

    };

    const [supplier, setSupplier] = useState(initialState);

    useEffect(() => {

        if (isEdit && selectedSupplier) {

            setSupplier({

                supplierCode: selectedSupplier.supplierCode || "",
                companyName: selectedSupplier.companyName || "",
                gstNumber: selectedSupplier.gstNumber || "",
                contactPerson: selectedSupplier.contactPerson || "",
                email: selectedSupplier.email || "",
                phone: selectedSupplier.phone || "",
                street: selectedSupplier.street || "",
                city: selectedSupplier.city || "",
                state: selectedSupplier.state || "",
                country: selectedSupplier.country || "",
                postalCode: selectedSupplier.postalCode || ""

            });

        } else {

            setSupplier(initialState);

        }

    }, [selectedSupplier, isEdit, open]);

    const handleChange = (e) => {

        setSupplier({

            ...supplier,

            [e.target.name]: e.target.value

        });

    };

    const handleSave = async () => {

        try {

            if (isEdit) {

                await updateSupplier(
                    selectedSupplier.supplierId,
                    supplier
                );

            } else {

                await createSupplier(supplier);

            }

            refreshSuppliers();

            handleClose();

            setSupplier(initialState);

        } catch (error) {

            console.error(error);

            alert(error.response?.data?.message || "Unable to save supplier.");

        }

    };

    return (

        <Dialog open={open} fullWidth maxWidth="md">

            <DialogTitle>

                {isEdit ? "Edit Supplier" : "Add Supplier"}

            </DialogTitle>

            <DialogContent>

                <Grid container spacing={2} sx={{ mt: 1 }}>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="Supplier Code" name="supplierCode" value={supplier.supplierCode} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="Company Name" name="companyName" value={supplier.companyName} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="GST Number" name="gstNumber" value={supplier.gstNumber} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="Contact Person" name="contactPerson" value={supplier.contactPerson} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="Email" name="email" value={supplier.email} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="Phone" name="phone" value={supplier.phone} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12 }}>
                        <TextField fullWidth label="Street" name="street" value={supplier.street} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:4 }}>
                        <TextField fullWidth label="City" name="city" value={supplier.city} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:4 }}>
                        <TextField fullWidth label="State" name="state" value={supplier.state} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:4 }}>
                        <TextField fullWidth label="Country" name="country" value={supplier.country} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="Postal Code" name="postalCode" value={supplier.postalCode} onChange={handleChange}/>
                    </Grid>

                </Grid>

            </DialogContent>

            <DialogActions>

                <Button onClick={handleClose}>Cancel</Button>

                <Button variant="contained" onClick={handleSave}>

                    {isEdit ? "Update Supplier" : "Save Supplier"}

                </Button>

            </DialogActions>

        </Dialog>

    );

}

export default SupplierDialog;