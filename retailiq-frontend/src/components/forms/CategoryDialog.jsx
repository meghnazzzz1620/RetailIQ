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
    createCategory,
    updateCategory
} from "../../services/categoryService";

function CategoryDialog({
    open,
    handleClose,
    refreshCategories,
    selectedCategory,
    isEdit
}) {

    const initialState = {

        categoryName: "",

        description: ""

    };

    const [category, setCategory] = useState(initialState);

    useEffect(() => {

        if (isEdit && selectedCategory) {

            setCategory({

                categoryName: selectedCategory.categoryName || "",

                description: selectedCategory.description || ""

            });

        } else {

            setCategory(initialState);

        }

    }, [selectedCategory, isEdit, open]);

    const handleChange = (e) => {

        setCategory({

            ...category,

            [e.target.name]: e.target.value

        });

    };

    const handleSave = async () => {

        try {

            if (isEdit) {

                await updateCategory(

                    selectedCategory.categoryId,

                    category

                );

            } else {

                await createCategory(category);

            }

            refreshCategories();

            setCategory(initialState);

            handleClose();

        } catch (error) {

            console.error(error);

            alert(

                error.response?.data?.message ||

                "Unable to save category."

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

                {isEdit ? "Edit Category" : "Add Category"}

            </DialogTitle>

            <DialogContent>

                <TextField
                    fullWidth
                    sx={{ mt: 2 }}
                    label="Category Name"
                    name="categoryName"
                    value={category.categoryName}
                    onChange={handleChange}
                />

                <TextField
                    fullWidth
                    multiline
                    rows={3}
                    sx={{ mt: 2 }}
                    label="Description"
                    name="description"
                    value={category.description}
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

                    {isEdit ? "Update Category" : "Save Category"}

                </Button>

            </DialogActions>

        </Dialog>

    );

}

export default CategoryDialog;