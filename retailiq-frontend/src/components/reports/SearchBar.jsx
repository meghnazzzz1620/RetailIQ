import { TextField } from "@mui/material";

function SearchBar({ value, onChange }) {

    return (

        <TextField

            label="Search"

            value={value}

            onChange={(e) => onChange(e.target.value)}

            size="small"

            sx={{ width: 300 }}

        />

    );

}

export default SearchBar;