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

import { useState } from "react";
import { createOrder } from "../../services/orderService";

function OrderDialog({ open, handleClose, refreshOrders }) {

    const [order, setOrder] = useState({

        customerId: "",

        employeeId: "",

        warehouseId: "",

        paymentMethod: "CASH",

        discount: 0,

        items: []

    });

    const handleChange = (e) => {

        setOrder({

            ...order,

            [e.target.name]: e.target.value

        });

    };

    const handleSave = async () => {

        try {

            await createOrder(order);

            refreshOrders();

            handleClose();

        }

        catch (error) {

            console.error(error);

            alert("Unable to create order.");

        }

    };

    return (

        <Dialog
            open={open}
            fullWidth
            maxWidth="sm"
        >

            <DialogTitle>

                Create Order

            </DialogTitle>

            <DialogContent>

                <Grid container spacing={2} sx={{ mt: 1 }}>

                    <Grid size={{ xs:12 }}>

                        <TextField
                            fullWidth
                            label="Customer ID"
                            name="customerId"
                            onChange={handleChange}
                        />

                    </Grid>

                    <Grid size={{ xs:12 }}>

                        <TextField
                            fullWidth
                            label="Employee ID"
                            name="employeeId"
                            onChange={handleChange}
                        />

                    </Grid>

                    <Grid size={{ xs:12 }}>

                        <TextField
                            fullWidth
                            label="Warehouse ID"
                            name="warehouseId"
                            onChange={handleChange}
                        />

                    </Grid>

                    <Grid size={{ xs:12 }}>

                        <TextField
                            select
                            fullWidth
                            label="Payment Method"
                            name="paymentMethod"
                            value={order.paymentMethod}
                            onChange={handleChange}
                        >

                            <MenuItem value="CASH">Cash</MenuItem>

                            <MenuItem value="CARD">Card</MenuItem>

                            <MenuItem value="UPI">UPI</MenuItem>

                        </TextField>

                    </Grid>

                    <Grid size={{ xs:12 }}>

                        <TextField
                            fullWidth
                            type="number"
                            label="Discount"
                            name="discount"
                            onChange={handleChange}
                        />

                    </Grid>

                    <Grid size={{ xs:12 }}>

                        <p>
                            Product selection UI will be added in Phase 2.
                        </p>

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

                    Create Order

                </Button>

            </DialogActions>

        </Dialog>

    );

}

export default OrderDialog;