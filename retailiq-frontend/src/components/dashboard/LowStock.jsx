import "./RecentOrders.css";

function LowStock(){

    const products=[

        {

            name:"Laptop",

            stock:3

        },

        {

            name:"Mouse",

            stock:5

        },

        {

            name:"Keyboard",

            stock:2

        }

    ];

    return(

        <div className="table-card">

            <h3>

                Low Stock Products

            </h3>

            <table>

                <thead>

                <tr>

                    <th>Product</th>

                    <th>Available Stock</th>

                </tr>

                </thead>

                <tbody>

                {

                    products.map(product=>(

                        <tr key={product.name}>

                            <td>{product.name}</td>

                            <td style={{color:"red"}}>

                                {product.stock}

                            </td>

                        </tr>

                    ))

                }

                </tbody>

            </table>

        </div>

    );

}

export default LowStock;