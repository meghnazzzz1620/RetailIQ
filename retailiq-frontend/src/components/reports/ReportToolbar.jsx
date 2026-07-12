import { Stack } from "@mui/material";

import SearchBar from "./SearchBar";
import ExportButtons from "./ExportButtons";

function ReportToolbar({

    search,

    setSearch,

    onPdf,

    onExcel,

    onCsv

}) {

    return (

        <Stack

            direction="row"

            justifyContent="space-between"

            alignItems="center"

            sx={{ mb: 3 }}

        >

            <SearchBar

                value={search}

                onChange={setSearch}

            />

            <ExportButtons

                onPdf={onPdf}

                onExcel={onExcel}

                onCsv={onCsv}

            />

        </Stack>

    );

}

export default ReportToolbar;