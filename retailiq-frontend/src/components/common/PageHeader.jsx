import "./PageHeader.css";

function PageHeader({ title, subtitle, buttonText, onClick }) {

    return (

        <div className="page-header">

            <div>

                <h1>{title}</h1>

                <p>{subtitle}</p>

            </div>

            <button onClick={onClick}>

                {buttonText}

            </button>

        </div>

    );

}

export default PageHeader;