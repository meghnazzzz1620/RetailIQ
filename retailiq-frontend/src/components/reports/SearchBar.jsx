import { TextField } from "@mui/material";

function SearchBar({

    value,

    onChange,

    placeholder = "Search"

}) {

    return (

        <TextField

            value={value}

            onChange={(e) => onChange(e.target.value)}

            placeholder={placeholder}

            size="small"

            sx={{ width: 300, mb: 2 }}

        />

    );

}

export default SearchBar;