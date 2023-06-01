import React from "react";

import "./CardField.css";

interface CardFieldProps {
    children: React.ReactNode;
}

const CardField: React.FC<CardFieldProps> =  ({ children }) => {
    return <div className='card-field'>{children}</div>
}

export default CardField;   