import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button,
    Grid,
    TextField
} from "@mui/material";

import { useState, useEffect } from "react";

import {
    createCustomer,
    updateCustomer
} from "../../services/customerService";

function CustomerDialog({
    open,
    handleClose,
    refreshCustomers,
    selectedCustomer,
    isEdit
}) {

    const initialState = {

        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        address: "",
        city: "",
        state: "",
        country: "",
        postalCode: ""

    };

    const [customer, setCustomer] = useState(initialState);

    useEffect(() => {

        if (isEdit && selectedCustomer) {

            setCustomer({

                firstName: selectedCustomer.firstName || "",
                lastName: selectedCustomer.lastName || "",
                email: selectedCustomer.email || "",
                phone: selectedCustomer.phone || "",
                address: selectedCustomer.address || "",
                city: selectedCustomer.city || "",
                state: selectedCustomer.state || "",
                country: selectedCustomer.country || "",
                postalCode: selectedCustomer.postalCode || ""

            });

        }

        else {

            setCustomer(initialState);

        }

    }, [selectedCustomer, isEdit, open]);

    const handleChange = (e) => {

        setCustomer({

            ...customer,

            [e.target.name]: e.target.value

        });

    };

    const handleSave = async () => {

        try {

            if (isEdit) {

                await updateCustomer(
                    selectedCustomer.customerId,
                    customer
                );

            }

            else {

                await createCustomer(customer);

            }

            refreshCustomers();

            handleClose();

            setCustomer(initialState);

        }

        catch (error) {

            console.error(error);

            alert(
                error.response?.data?.message ||
                "Unable to save customer."
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

                {isEdit ? "Edit Customer" : "Add Customer"}

            </DialogTitle>

            <DialogContent>

                <Grid container spacing={2} sx={{ mt: 1 }}>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="First Name" name="firstName" value={customer.firstName} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="Last Name" name="lastName" value={customer.lastName} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="Email" name="email" value={customer.email} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="Phone" name="phone" value={customer.phone} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12 }}>
                        <TextField fullWidth label="Address" name="address" value={customer.address} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:4 }}>
                        <TextField fullWidth label="City" name="city" value={customer.city} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:4 }}>
                        <TextField fullWidth label="State" name="state" value={customer.state} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:4 }}>
                        <TextField fullWidth label="Country" name="country" value={customer.country} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{ xs:12, md:6 }}>
                        <TextField fullWidth label="Postal Code" name="postalCode" value={customer.postalCode} onChange={handleChange}/>
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

                    {isEdit ? "Update Customer" : "Save Customer"}

                </Button>

            </DialogActions>

        </Dialog>

    );

}

export default CustomerDialog;