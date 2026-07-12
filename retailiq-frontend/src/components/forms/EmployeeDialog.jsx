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
    createEmployee,
    updateEmployee
} from "../../services/employeeService";

function EmployeeDialog({
    open,
    handleClose,
    refreshEmployees,
    selectedEmployee,
    isEdit
}) {

    const initialState = {

        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        designation: "",
        department: "",
        salary: ""

    };

    const [employee, setEmployee] = useState(initialState);

    useEffect(() => {

        if (isEdit && selectedEmployee) {

            setEmployee({

                firstName: selectedEmployee.firstName || "",
                lastName: selectedEmployee.lastName || "",
                email: selectedEmployee.email || "",
                phone: selectedEmployee.phone || "",
                designation: selectedEmployee.designation || "",
                department: selectedEmployee.department || "",
                salary: selectedEmployee.salary || ""

            });

        } else {

            setEmployee(initialState);

        }

    }, [selectedEmployee, isEdit, open]);

    const handleChange = (e) => {

        setEmployee({

            ...employee,

            [e.target.name]: e.target.value

        });

    };

    const handleSave = async () => {

        try {

            const payload = {

                ...employee,

                salary: Number(employee.salary)

            };

            if (isEdit) {

                await updateEmployee(
                    selectedEmployee.employeeId,
                    payload
                );

            } else {

                await createEmployee(payload);

            }

            refreshEmployees();

            handleClose();

            setEmployee(initialState);

        } catch (error) {

            console.error(error);

            alert(
                error.response?.data?.message ||
                "Unable to save employee."
            );

        }

    };

    return (

        <Dialog open={open} fullWidth maxWidth="md">

            <DialogTitle>

                {isEdit ? "Edit Employee" : "Add Employee"}

            </DialogTitle>

            <DialogContent>

                <Grid container spacing={2} sx={{ mt: 1 }}>

                    <Grid size={{xs:12, md:6}}>
                        <TextField fullWidth label="First Name" name="firstName" value={employee.firstName} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{xs:12, md:6}}>
                        <TextField fullWidth label="Last Name" name="lastName" value={employee.lastName} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{xs:12, md:6}}>
                        <TextField fullWidth label="Email" name="email" value={employee.email} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{xs:12, md:6}}>
                        <TextField fullWidth label="Phone" name="phone" value={employee.phone} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{xs:12, md:6}}>
                        <TextField fullWidth label="Designation" name="designation" value={employee.designation} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{xs:12, md:6}}>
                        <TextField fullWidth label="Department" name="department" value={employee.department} onChange={handleChange}/>
                    </Grid>

                    <Grid size={{xs:12, md:6}}>
                        <TextField fullWidth type="number" label="Salary" name="salary" value={employee.salary} onChange={handleChange}/>
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

                    {isEdit ? "Update Employee" : "Save Employee"}

                </Button>

            </DialogActions>

        </Dialog>

    );

}

export default EmployeeDialog;