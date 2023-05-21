import React from "react";
import back_card from "../../assets/cards/back_card.svg";

import "./Card.css";

const Card = ({ src, hidden, onClick }) => {

    return <img onClick={onClick} src={hidden === true ? back_card : src} />
}

export default Card;