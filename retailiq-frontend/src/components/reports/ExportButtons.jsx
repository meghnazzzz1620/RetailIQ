import { Button, Stack } from "@mui/material";
import PictureAsPdfIcon from "@mui/icons-material/PictureAsPdf";
import TableViewIcon from "@mui/icons-material/TableView";
import DownloadIcon from "@mui/icons-material/Download";

function ExportButtons({ onPdf, onExcel, onCsv }) {

    return (

        <Stack
            direction="row"
            spacing={2}
        >

            <Button
                variant="contained"
                color="error"
                startIcon={<PictureAsPdfIcon />}
                onClick={onPdf}
            >
                PDF
            </Button>

            <Button
                variant="contained"
                color="success"
                startIcon={<TableViewIcon />}
                onClick={onExcel}
            >
                Excel
            </Button>

            <Button
                variant="contained"
                startIcon={<DownloadIcon />}
                onClick={onCsv}
            >
                CSV
            </Button>

        </Stack>

    );

}

export default ExportButtons;