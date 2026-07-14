import { TextField } from "@mui/material";

function SearchBar({

    value = "",

    onChange,

    placeholder = "Search"

}) {

    return (

        <TextField

            fullWidth={false}

            size="small"

            placeholder={placeholder}

            value={value}

            onChange={(e) => {

                console.log("Typing:", e.target.value);

                onChange(e.target.value);

            }}

            sx={{ width: 350 }}

        />

    );

}

export default SearchBar;