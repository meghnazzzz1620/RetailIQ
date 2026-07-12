import { useEffect, useState } from "react";
import { IconButton } from "@mui/material";
import { Edit, Delete } from "@mui/icons-material";

import DashboardLayout from "../../layouts/DashboardLayout";
import PageHeader from "../../components/common/PageHeader";
import SearchBar from "../../components/common/SearchBar";
import CommonTable from "../../components/tables/CommonTable";
import EmployeeDialog from "../../components/forms/EmployeeDialog";

import {
    getAllEmployees,
    getEmployeeById,
    deleteEmployee
} from "../../services/employeeService";

function Employees() {

    const [open, setOpen] = useState(false);

    const [employees, setEmployees] = useState([]);

    const [selectedEmployee, setSelectedEmployee] = useState(null);

    const [isEdit, setIsEdit] = useState(false);

    const loadEmployees = async () => {

        try {

            const data = await getAllEmployees();

            setEmployees(data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadEmployees();

    }, []);

    const handleOpen = () => {

        setSelectedEmployee(null);

        setIsEdit(false);

        setOpen(true);

    };

    const handleClose = () => {

        setOpen(false);

    };

    const handleEdit = async (id) => {

        try {

            const employee = await getEmployeeById(id);

            setSelectedEmployee(employee);

            setIsEdit(true);

            setOpen(true);

        } catch (error) {

            console.error(error);

            alert("Unable to load employee.");

        }

    };

    const handleDelete = async (id) => {

        if (!window.confirm("Are you sure you want to delete this employee?")) {

            return;

        }

        try {

            await deleteEmployee(id);

            loadEmployees();

        } catch (error) {

            console.error(error);

            alert("Unable to delete employee.");

        }

    };

    const columns = [

        {
            field: "id",
            headerName: "ID",
            width: 80
        },

        {
            field: "firstName",
            headerName: "First Name",
            flex: 1
        },

        {
            field: "lastName",
            headerName: "Last Name",
            flex: 1
        },

        {
            field: "designation",
            headerName: "Designation",
            flex: 1
        },

        {
            field: "department",
            headerName: "Department",
            flex: 1
        },

        {
            field: "salary",
            headerName: "Salary",
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

    const rows = employees.map(employee => ({

        id: employee.employeeId,

        employeeId: employee.employeeId,

        firstName: employee.firstName,

        lastName: employee.lastName,

        designation: employee.designation,

        department: employee.department,

        salary: employee.salary

    }));

    return (

        <DashboardLayout>

            <PageHeader
                title="Employees"
                subtitle="Manage Employees"
                buttonText="+ Add Employee"
                onClick={handleOpen}
            />

            <SearchBar
                placeholder="Search Employees..."
            />

            <CommonTable
                rows={rows}
                columns={columns}
            />

            <EmployeeDialog
                open={open}
                handleClose={handleClose}
                refreshEmployees={loadEmployees}
                selectedEmployee={selectedEmployee}
                isEdit={isEdit}
            />

        </DashboardLayout>

    );

}

export default Employees;