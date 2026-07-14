import { useEffect, useState } from "react";
import { IconButton } from "@mui/material";
import { Edit, Delete } from "@mui/icons-material";

import DashboardLayout from "../../layouts/DashboardLayout";
import PageHeader from "../../components/common/PageHeader";
import SearchBar from "../../components/common/SearchBar";
import CommonTable from "../../components/tables/CommonTable";
import ProductDialog from "../../components/forms/ProductDialog";

import {
    getAllProducts,
    getProductById,
    deleteProduct,
    searchProducts
} from "../../services/productService";

function Products() {

    const [open, setOpen] = useState(false);

    const [products, setProducts] = useState([]);

    const [selectedProduct, setSelectedProduct] = useState(null);

    const [isEdit, setIsEdit] = useState(false);

    const [keyword, setKeyword] = useState("");

    const loadProducts = async () => {

        try {

            const data = await getAllProducts();

            setProducts(data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadProducts();

    }, []);

    useEffect(() => {

        const timer = setTimeout(async () => {

            try {

                if (keyword.trim() === "") {

                    loadProducts();

                } else {

                    const data = await searchProducts(keyword);

                    setProducts(data);

                }

            } catch (error) {

                console.error(error);

            }

        }, 300);

        return () => clearTimeout(timer);

    }, [keyword]);

    const handleOpen = () => {

        setSelectedProduct(null);

        setIsEdit(false);

        setOpen(true);

    };

    const handleClose = () => {

        setOpen(false);

    };

    const handleEdit = async (id) => {

        try {

            const product = await getProductById(id);

            setSelectedProduct(product);

            setIsEdit(true);

            setOpen(true);

        } catch (error) {

            console.error(error);

            alert("Unable to load product.");

        }

    };

    const handleDelete = async (id) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this product?"
        );

        if (!confirmDelete) return;

        try {

            await deleteProduct(id);

            loadProducts();

        } catch (error) {

            console.error(error);

            alert("Unable to delete product.");

        }

    };

    const columns = [

        {
            field: "id",
            headerName: "ID",
            width: 80
        },

        {
            field: "productName",
            headerName: "Product Name",
            flex: 1
        },

        {
            field: "sku",
            headerName: "SKU",
            flex: 1
        },

        {
            field: "purchasePrice",
            headerName: "Purchase Price",
            flex: 1
        },

        {
            field: "sellingPrice",
            headerName: "Selling Price",
            flex: 1
        },

        {
            field: "gstPercentage",
            headerName: "GST %",
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

    const rows = products.map((product) => ({

        id: product.productId,

        productName: product.productName,

        sku: product.sku,

        purchasePrice: product.purchasePrice,

        sellingPrice: product.sellingPrice,

        gstPercentage: product.gstPercentage

    }));

    return (

        <DashboardLayout>

            <PageHeader
                title="Products"
                subtitle="Manage all products"
                buttonText="+ Add Product"
                onClick={handleOpen}
            />

            <SearchBar
                placeholder="Search Products..."
                value={keyword}
                onChange={setKeyword}
            />

            <CommonTable
                rows={rows}
                columns={columns}
            />

            <ProductDialog
                open={open}
                handleClose={handleClose}
                refreshProducts={loadProducts}
                selectedProduct={selectedProduct}
                isEdit={isEdit}
            />

        </DashboardLayout>

    );

}

export default Products;