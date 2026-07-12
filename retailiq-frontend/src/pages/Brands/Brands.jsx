import { useEffect, useState } from "react";
import { IconButton } from "@mui/material";
import { Edit, Delete } from "@mui/icons-material";

import DashboardLayout from "../../layouts/DashboardLayout";
import PageHeader from "../../components/common/PageHeader";
import SearchBar from "../../components/common/SearchBar";
import CommonTable from "../../components/tables/CommonTable";
import BrandDialog from "../../components/forms/BrandDialog";

import {
    getAllBrands,
    getBrandById,
    deleteBrand
} from "../../services/brandService";

function Brands() {

    const [open, setOpen] = useState(false);

    const [brands, setBrands] = useState([]);

    const [selectedBrand, setSelectedBrand] = useState(null);

    const [isEdit, setIsEdit] = useState(false);

    const loadBrands = async () => {

        try {

            const data = await getAllBrands();

            setBrands(data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadBrands();

    }, []);

    const handleOpen = () => {

        setSelectedBrand(null);

        setIsEdit(false);

        setOpen(true);

    };

    const handleClose = () => {

        setOpen(false);

    };

    const handleEdit = async (id) => {

        try {

            const brand = await getBrandById(id);

            setSelectedBrand(brand);

            setIsEdit(true);

            setOpen(true);

        } catch (error) {

            console.error(error);

            alert("Unable to load brand.");

        }

    };

    const handleDelete = async (id) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this brand?"
        );

        if (!confirmDelete) return;

        try {

            await deleteBrand(id);

            loadBrands();

        } catch (error) {

            console.error(error);

            alert("Unable to delete brand.");

        }

    };

    const columns = [

        {
            field: "id",
            headerName: "ID",
            width: 80
        },

        {
            field: "brandName",
            headerName: "Brand Name",
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

    const rows = brands.map((brand) => ({

        id: brand.brandId,

        brandId: brand.brandId,

        brandName: brand.brandName,

        description: brand.description

    }));

    return (

        <DashboardLayout>

            <PageHeader
                title="Brands"
                subtitle="Manage Brands"
                buttonText="+ Add Brand"
                onClick={handleOpen}
            />

            <SearchBar
                placeholder="Search Brands..."
            />

            <CommonTable
                rows={rows}
                columns={columns}
            />

            <BrandDialog
                open={open}
                handleClose={handleClose}
                refreshBrands={loadBrands}
                selectedBrand={selectedBrand}
                isEdit={isEdit}
            />

        </DashboardLayout>

    );

}

export default Brands;