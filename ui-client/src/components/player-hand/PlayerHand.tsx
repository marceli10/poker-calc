import React from "react";

import "./PlayerHand.css";

const PlayerHand = ( { firstCard, secondCard, children }) => {
    return (
        <div className="player-box">
            {children}
        </div>
    )
}

export default PlayerHand;