import React from "react";

import "./RecButton.css";

interface RecButtonProps {
    handleClick: React.MouseEventHandler<HTMLButtonElement>;
    children: React.ReactNode;
}

const RecButton: React.FC<RecButtonProps> = ({handleClick, children}) => {
    return <button onClick={handleClick} className='rec-button' >{children}</button>
}

export default RecButton;