import React, { useState } from "react";
import * as cardName from '../../AllImages';
import colors from "./Colors"

import "./Card.css";
import values from "./Values";
import RecButton from "../rec-button/RecButton";

interface CardProps {
    src: string;
    handleCardClick: (player: string, card: string, value: string, color: string) => void;
    handleDealerCardClick: (cardIndex: number, value: string, color: string) => void;
    player: string;
    card: string;
    cardIndex: number;
}

const Card: React.FC<CardProps> = ({ src, handleCardClick, handleDealerCardClick, cardIndex, player, card }) => {

    const [newCard, setNewCard] = useState({
        color: colors[0],
        value: values[0]
    })

    const [chooser, setChooser] = useState(false);

    let path = '/src/assets/cards/';

    const handleClick = () => {
        setChooser(true);
    }

    const handleOkClick = () => {

        let newValue = '';
        let newColor = '';

        if (newCard.color !== "BACK") {
            newValue = newCard.value;
            newColor = newCard.color;
        }

        setChooser(false);

        if (player === 'dealer') {
            handleDealerCardClick(cardIndex, newValue, newColor);
        } else {
            handleCardClick(player, card, newColor, newValue);
        }
    }

    const handleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const { name, value } = e.target;

        setNewCard(prevInfo => {
            return {
                ...prevInfo,
                [name]: value
            }
        })
    }

    return (
        <>
            {chooser &&
                <div className="app-cover">
                    <div className="image-choose-container">
                        <h1 className="choose-header">Choose a card</h1>
                        <div className="choosers-container">
                            <select onChange={handleChange} name="color" value={newCard.color}>
                                {colors.map((color, index) => (
                                    <option key={index} value={color}>{color}</option>
                                ))}
                            </select>

                            <select onChange={handleChange} name="value" value={newCard.value}>
                                {values.map((value, index) => (
                                    <option key={index} value={value}>{value}</option>
                                ))}
                            </select>
                        </div>
                        <RecButton handleClick={handleOkClick} >OK</RecButton>
                    </div>
                </div>
            }
            <img className="card-image" onClick={handleClick} src={src === '_.svg' ? cardName.back_card : path + src} />

        </>
    )
}

export default Card;