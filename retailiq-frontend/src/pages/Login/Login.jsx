import "./Login.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../../services/authService";

function Login() {

    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async (e) => {

        e.preventDefault();

        try {

            const response = await login(email, password);

            localStorage.setItem("token", response.token);
            localStorage.setItem("email", response.email);
            localStorage.setItem("role", response.role);

            navigate("/dashboard");

        } catch (err) {

            alert("Invalid Email or Password");

        }

    };

    return (

        <div className="login-container">

            <div className="login-card">

                <h1 className="logo">
                    RetailIQ
                </h1>

                <p className="subtitle">
                    Enterprise Retail Intelligence Platform
                </p>

                <form onSubmit={handleLogin}>

                    <label>Email</label>

                    <input
                        type="email"
                        placeholder="Enter your email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />

                    <label>Password</label>

                    <input
                        type="password"
                        placeholder="Enter your password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />

                    <button type="submit">

                        Sign In

                    </button>

                </form>

                <p className="forgot">

                    Forgot Password?

                </p>

            </div>

        </div>

    );

}

export default Login;