import React from 'react';
import "./PlayerHand.css";
import CardField from '../card-field/CardField';
import Card from '../card/Card';

interface CardData {
    value: string;
    color: string
}

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
                    <Card src={`${firstCard.value}_${firstCard.color}.svg`} player={player} card='firstCard' handleCardClick={handleCardClick}/>
                </CardField>

                <CardField>
                    <Card src={`${secondCard.value}_${secondCard.color}.svg`} player={player} card='secondCard' handleCardClick={handleCardClick}/>
                </CardField>
            </div>
            <p>Chance: </p>
        </div>
    )
}

export default PlayerHand; 