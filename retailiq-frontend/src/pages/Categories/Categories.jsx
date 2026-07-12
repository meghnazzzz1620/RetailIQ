import { useEffect, useState } from "react";
import { IconButton } from "@mui/material";
import { Edit, Delete } from "@mui/icons-material";

import DashboardLayout from "../../layouts/DashboardLayout";
import PageHeader from "../../components/common/PageHeader";
import SearchBar from "../../components/common/SearchBar";
import CommonTable from "../../components/tables/CommonTable";
import CategoryDialog from "../../components/forms/CategoryDialog";

import {
    getAllCategories,
    getCategoryById,
    deleteCategory
} from "../../services/categoryService";

function Categories() {

    const [open, setOpen] = useState(false);

    const [categories, setCategories] = useState([]);

    const [selectedCategory, setSelectedCategory] = useState(null);

    const [isEdit, setIsEdit] = useState(false);

    const loadCategories = async () => {

        try {

            const data = await getAllCategories();

            setCategories(data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadCategories();

    }, []);

    const handleOpen = () => {

        setSelectedCategory(null);

        setIsEdit(false);

        setOpen(true);

    };

    const handleClose = () => {

        setOpen(false);

    };

    const handleEdit = async (id) => {

        try {

            const category = await getCategoryById(id);

            setSelectedCategory(category);

            setIsEdit(true);

            setOpen(true);

        } catch (error) {

            console.error(error);

            alert("Unable to load category.");

        }

    };

    const handleDelete = async (id) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this category?"
        );

        if (!confirmDelete) return;

        try {

            await deleteCategory(id);

            loadCategories();

        } catch (error) {

            console.error(error);

            alert("Unable to delete category.");

        }

    };

    const columns = [

        {
            field: "id",
            headerName: "ID",
            width: 80
        },

        {
            field: "categoryName",
            headerName: "Category Name",
            flex: 1
        },

        {
            field: "description",
            headerName: "Description",
            flex: 2
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

    const rows = categories.map((category) => ({

        id: category.categoryId,

        categoryId: category.categoryId,

        categoryName: category.categoryName,

        description: category.description

    }));

    return (

        <DashboardLayout>

            <PageHeader
                title="Categories"
                subtitle="Manage Categories"
                buttonText="+ Add Category"
                onClick={handleOpen}
            />

            <SearchBar
                placeholder="Search Categories..."
            />

            <CommonTable
                rows={rows}
                columns={columns}
            />

            <CategoryDialog
                open={open}
                handleClose={handleClose}
                refreshCategories={loadCategories}
                selectedCategory={selectedCategory}
                isEdit={isEdit}
            />

        </DashboardLayout>

    );

}

export default Categories;