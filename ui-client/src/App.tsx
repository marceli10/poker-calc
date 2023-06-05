import React, {useState} from 'react'
import PlayerHand, {CardData, PlayerHandData} from './components/player-hand/PlayerHand';
import './App.css';
import DealerHand from './components/dealer-hand/DealerHand';
import RecButton from './components/rec-button/RecButton';
import axios from "axios";
import _, {isEmpty} from "lodash";


const initialPlayersState = {
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
};
const initialCardsOnTableState = {
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
};

const initialPlayersStateWithValues = {
    firstPlayer: {
        firstCard: {
            color: 'HEART',
            value: 'TWO'
        },
        secondCard: {
            color: 'SPADE',
            value: 'THREE'
        }
    },

    secondPlayer: {
        firstCard: {
            value: 'FOUR',
            color: 'SPADE'
        },
        secondCard: {
            value: 'FIVE',
            color: 'SPADE'
        }
    },

    thirdPlayer: {
        firstCard: {
            value: 'ACE',
            color: 'HEART'
        },
        secondCard: {
            value: 'ACE',
            color: 'SPADE'
        }
    },

    fourthPlayer: {
        firstCard: {
            value: 'KING',
            color: 'SPADE'
        },
        secondCard: {
            value: 'KING',
            color: 'CLUB'
        }
    },

    fifthPlayer: {
        firstCard: {
            value: 'QUEEN',
            color: 'CLUB'
        },
        secondCard: {
            value: 'JACK',
            color: 'CLUB'
        }
    },

    sixthPlayer: {
        firstCard: {
            value: 'SEVEN',
            color: 'DIAMOND'
        },
        secondCard: {
            value: 'SIX',
            color: 'DIAMOND'
        }
    }
};

type RequestBody = {
    firstPlayer: PlayerHandData,
    secondPlayer: PlayerHandData,
    thirdPlayer: PlayerHandData,
    fourthPlayer: PlayerHandData,
    fifthPlayer: PlayerHandData,
    sixthPlayer: PlayerHandData,
    cardsOnTable?: {
        cards: CardData[]
    }
};

type ChanceStatistics = {
    combinationChances: {
        PAIR: number,
        TWO_PAIRS: number,
        THREE_OF_A_KING: number,
        STRAIGHT: number,
        FLUSH: number,
        FULL_HOUSE: number,
        FOUR_OF_A_KING: number,
        STRAIGHT_FLUSH: number,
        ROYAL_FLUSH: number,
    },
    firstPlayerChance: number,
    secondPlayerChance: number,
    thirdPlayerChance: number,
    fourthPlayerChance: number,
    fifthPlayerChance: number,
    sixthPlayerChance: number,
}

function App() {

    const [cardsOnTable, setCardsOnTable] = useState(initialCardsOnTableState);
    const [players, setPlayers] = useState(initialPlayersStateWithValues);
    const [playersChances, setPlayersChances] = useState<ChanceStatistics>();
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

    const handleCalculate = async (e: React.MouseEvent<HTMLButtonElement>) => {
        e.preventDefault();
        const cardsOnTableToSend: CardData[] = cardsOnTable.cards.filter(cards => cards.color && cards.value);
        const requestBody = {
            ...players,
            cardsOnTable: !_.isEmpty(cardsOnTableToSend) ? {cards: cardsOnTableToSend} : undefined
        };
        console.log(requestBody);
        await axios.post('http://localhost:8080/api/poker-calc', requestBody)
            .then(response => {setPlayersChances(response.data);console.log(response.data)})
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
                    chances={playersChances?.firstPlayerChance}
                />

                <PlayerHand
                    firstCard={players.secondPlayer.firstCard}
                    secondCard={players.secondPlayer.secondCard}
                    playerName='Player 2'
                    handleCardClick={handleCardClick}
                    player='secondPlayer'
                    chances={playersChances?.secondPlayerChance}
                />

                <PlayerHand
                    firstCard={players.thirdPlayer.firstCard}
                    secondCard={players.thirdPlayer.secondCard}
                    playerName='Player 3'
                    handleCardClick={handleCardClick}
                    player='thirdPlayer'
                    chances={playersChances?.thirdPlayerChance}
                />

                <PlayerHand
                    firstCard={players.fourthPlayer.firstCard}
                    secondCard={players.fourthPlayer.secondCard}
                    playerName='Player 4'
                    handleCardClick={handleCardClick}
                    player='fourthPlayer'
                    chances={playersChances?.fourthPlayerChance}
                />

                <PlayerHand
                    firstCard={players.fifthPlayer.firstCard}
                    secondCard={players.fifthPlayer.secondCard}
                    playerName='Player 5'
                    handleCardClick={handleCardClick}
                    player='fifthPlayer'
                    chances={playersChances?.fifthPlayerChance}
                />

                <PlayerHand
                    firstCard={players.sixthPlayer.firstCard}
                    secondCard={players.sixthPlayer.secondCard}
                    playerName='Player 6'
                    handleCardClick={handleCardClick}
                    player='sixthPlayer'
                    chances={playersChances?.sixthPlayerChance}
                />

            </div>
            <div className='button-container'>
                <RecButton handleClick={handleCalculate}>Calculate</RecButton>
            </div>
        </div>
    );
}

export default App;
