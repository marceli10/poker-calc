import React from "react";

import "./DealerHand.css";
import CardField from "../card-field/CardField";
import Card from "../card/Card";

interface CardData {
    value: string;
    color: string;
}

interface DealerHandProps {
    player: string;
    handleCardClick: (cardIndex: number, value: string, color: string) => void;
    cards: Array<CardData>
}

const DealerHand: React.FC<DealerHandProps> = ({ cards, handleCardClick, player }) => {

    return (
        <div className="dealer-hand">
            <h2>Dealer</h2>
            <div className='dealer-cards-wrapper'>
                {cards.map((card, index) => (
                    <CardField key={index}>
                        <Card
                            src={`${card.color}_${card.value}.svg`}
                            player={player}
                            handleDealerCardClick={handleCardClick}
                            cardIndex={index}
                        />
                    </CardField>
                ))}
            </div>
        </div>
    )
}

export default DealerHand;