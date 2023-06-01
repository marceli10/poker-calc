import React, { useState } from 'react'
import PlayerHand from './components/player-hand/PlayerHand';
import './App.css';
import DealerHand from './components/dealer-hand/DealerHand';
import RecButton from './components/rec-button/RecButton';

function App() {

    const [cardsOnTable, setCardsOnTable] = useState({
        cards: [
            {
                value: '',
                color: ''
            },
            {
                value: '',
                color: ''
            },
            {
                value: '',
                color: ''
            },
            {
                value: '',
                color: ''
            },
            {
                value: '',
                color: ''
            }
        ]
    })

    const [players, setPlayers] = useState({
        firstPlayer: {
            firstCard: {
                value: '',
                color: ''
            },
            secondCard: {
                value: '',
                color: ''
            }
        },

        secondPlayer: {
            firstCard: {
                value: '',
                color: ''
            },
            secondCard: {
                value: '',
                color: ''
            }
        },

        thirdPlayer: {
            firstCard: {
                value: '',
                color: ''
            },
            secondCard: {
                value: '',
                color: ''
            }
        },

        fourthPlayer: {
            firstCard: {
                value: '',
                color: ''
            },
            secondCard: {
                value: '',
                color: ''
            }
        },

        fifthPlayer: {
            firstCard: {
                value: '',
                color: ''
            },
            secondCard: {
                value: '',
                color: ''
            }
        },

        sixthPlayer: {
            firstCard: {
                value: '',
                color: ''
            },
            secondCard: {
                value: '',
                color: ''
            }
        }
    });

    const handleDealerCardChange = (cardIndex: number, value: string, color: string) => {
        setCardsOnTable(prevCards => {
            const updatedCards = [...cardsOnTable.cards];
            updatedCards[cardIndex] = {
                value: value,
                color: color
            }
            
            return {
                ...prevCards,
                cards: updatedCards
            }
        })
    }

    const handleCardClick = (player: string, card: string, value: string, color: string) => {
        setPlayers(prevCards => {
            return {
                ...prevCards,
                [player]: {
                    ...prevCards[player],
                    [card]: {
                        color: color,
                        value: value
                    }
                }
            }
        })
    }

    const handleCalculate = (e: React.MouseEvent<HTMLButtonElement>) => {
        e.preventDefault();

        const cardsOnTableToSend = cardsOnTable.cards.filter(cards => cards.color !== '' && cards.value !== '');

        console.log(players);
        console.log(cardsOnTableToSend);
    }

    return (
        <div className="main-wrapper">
            <div className="dealer-hand-container">
                <DealerHand
                    cards={cardsOnTable.cards}
                    handleCardClick={handleDealerCardChange}
                    player='dealer'
                />
            </div>
            <div className='player-hands-container'>
                <PlayerHand
                    firstCard={players.firstPlayer.firstCard}
                    secondCard={players.firstPlayer.secondCard}
                    handleCardClick={handleCardClick}
                    playerName='Player 1'
                    player='firstPlayer'
                />

                <PlayerHand
                    firstCard={players.secondPlayer.firstCard}
                    secondCard={players.secondPlayer.secondCard}
                    playerName='Player 2'
                    handleCardClick={handleCardClick}
                    player='secondPlayer'
                />

                <PlayerHand
                    firstCard={players.thirdPlayer.firstCard}
                    secondCard={players.thirdPlayer.secondCard}
                    playerName='Player 3'
                    handleCardClick={handleCardClick}
                    player='thirdPlayer'
                />

                <PlayerHand
                    firstCard={players.fourthPlayer.firstCard}
                    secondCard={players.fourthPlayer.secondCard}
                    playerName='Player 4'
                    handleCardClick={handleCardClick}
                    player='fourthPlayer'
                />

                <PlayerHand
                    firstCard={players.fifthPlayer.firstCard}
                    secondCard={players.fifthPlayer.secondCard}
                    playerName='Player 5'
                    handleCardClick={handleCardClick}
                    player='fifthPlayer'
                />

                <PlayerHand
                    firstCard={players.sixthPlayer.firstCard}
                    secondCard={players.sixthPlayer.secondCard}
                    playerName='Player 6'
                    handleCardClick={handleCardClick}
                    player='sixthPlayer'
                />

            </div>
            <div className='button-container'>
                <RecButton handleClick={handleCalculate} >Calculate</RecButton>
            </div>
        </div>
    );
}

export default App;
