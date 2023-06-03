import React from 'react';
import "./PlayerHand.css";
import CardField from '../card-field/CardField';
import Card from '../card/Card';

export type CardData = {
    value: string;
    color: string
};

export type PlayerHandData = {
    firstCard: CardData,
    secondCard: CardData
};

interface PlayerHandProps {
    playerName: string;
    firstCard: CardData;
    secondCard: CardData;
    handleCardClick: Function;
    player: string;
}

const PlayerHand: React.FC<PlayerHandProps> = ({ playerName, firstCard, secondCard, handleCardClick, player }) => {

    return (
        <div className="player-hand">
            <h2>{playerName}</h2>
            <div className='cards-wrapper'>
                <CardField>
                    <Card src={`${firstCard.color}_${firstCard.value}.svg`} player={player} card='firstCard' handleCardClick={handleCardClick}/>
                </CardField>

                <CardField>
                    <Card src={`${secondCard.color}_${secondCard.value}.svg`} player={player} card='secondCard' handleCardClick={handleCardClick}/>
                </CardField>
            </div>
            <p>Chance: </p>
        </div>
    )
}

export default PlayerHand; 