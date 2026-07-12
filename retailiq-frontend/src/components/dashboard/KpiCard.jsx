import "./KpiCard.css";

function KpiCard({ title, value, color }) {

    return (

        <div
            className="kpi-card"
            style={{
                borderTop: `5px solid ${color}`
            }}
        >

            <h4>{title}</h4>

            <h1>{value}</h1>

        </div>

    );

}

export default KpiCard;