import { DataGrid } from "@mui/x-data-grid";

function CommonTable({ rows, columns }) {

    return (

        <div
            style={{
                height: 500,
                width: "100%",
                marginTop: "25px",
                background: "white",
                borderRadius: "12px"
            }}
        >

            <DataGrid

                rows={rows}

                columns={columns}

                pageSizeOptions={[5, 10, 20]}

                initialState={{
                    pagination: {
                        paginationModel: {
                            pageSize: 5
                        }
                    }
                }}

                disableRowSelectionOnClick

            />

        </div>

    );

}

export default CommonTable;