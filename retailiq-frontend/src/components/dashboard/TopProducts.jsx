import "./RecentOrders.css";

import { useEffect, useState } from "react";

import { getTopSellingProducts } from "../../services/dashboardService";

function TopProducts() {

    const [products, setProducts] = useState([]);

    useEffect(() => {

        loadProducts();

    }, []);

    const loadProducts = async () => {

        try {

            const data = await getTopSellingProducts();

            setProducts(data);

        }

        catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="table-card">

            <h3>

                Top Selling Products

            </h3>

            <table>

                <thead>

                <tr>

                    <th>Product</th>

                    <th>Quantity Sold</th>

                </tr>

                </thead>

                <tbody>

                {

                    products.map((product) => (

                        <tr key={product.productId}>

                            <td>{product.productName}</td>

                            <td>{product.quantitySold}</td>

                        </tr>

                    ))

                }

                </tbody>

            </table>

        </div>

    );

}

export default TopProducts;