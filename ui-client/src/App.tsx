import React, {useState} from 'react'
import PlayerHand from './components/player-hand/PlayerHand';
import './App.css';
import * as cardName from './AllImages';
import {default as CardComponent}  from './components/card/Card';
import {back_card, clubs_ace} from "./AllImages";

type Card = {
    value: string;
    color: string;
};

type PlayerHand = {
    firstCard: Card,
    secondCard: Card
};

function App() {

    const [players, setPlayers] = useState({
        firstPlayer: {
            firstCard: {
                value: back_card,
                color: ''
            },
            secondCard: {
                value: back_card,
                color: ''
            }
        },

        secondPlayer: {
            firstCard: {
                value: back_card,
                color: ''
            },
            secondCard: {
                value: back_card,
                color: ''
            }
        },

        thirdPlayer: {
            firstCard: {
                value: back_card,
                    color: ''
            },
            secondCard: {
                value: back_card,
                    color: ''
            }
        },

        fourthPlayer: {
            firstCard: {
                value: back_card,
                    color: ''
            },
            secondCard: {
                value: back_card,
                color: ''
            }
        },

        fifthPlayer: {
            firstCard: {
                value: back_card,
                color: ''
            },
            secondCard: {
                value: back_card,
                color: ''
            }
        },

        sixthPlayer: {
            firstCard: {
                value: back_card,
                color: ''
            },
            secondCard: {
                value: back_card,
                color: ''
            }
        }
    });

    const changeFirstCard = (event) => {
        const {name} = event.target;

        setPlayers(prevInfo => {
            return {
                ...prevInfo,
                [name]: {
                    ...prevInfo[name],
                    firstCard: {
                        ...prevInfo[name].firstCard,
                        value: clubs_ace
                    }
                }
            }
        });
    }

    const changeSecondCard = (event) => {
        const {name} = event.target;

        setPlayers(prevInfo => {
            return {
                ...prevInfo,
                [name]: {
                    ...prevInfo[name],
                    secondCard: {
                        ...prevInfo[name].secondCard,
                        value: clubs_ace
                    }
                }
            }
        });
    }

    return (
        <div className="main-wrapper">
            <div className="dealer-hand-container">
                <h1>CISZA! Programuję.</h1>
            </div>

            <div className="player-hands-container">
                <PlayerHand>
                    <CardComponent onClick={changeFirstCard} name={players.firstPlayer} src={players.firstPlayer.firstCard?.value}/>
                    <CardComponent onClick={changeSecondCard} name={players.firstPlayer} src={players.firstPlayer.secondCard?.value}/>
                </PlayerHand>

                <PlayerHand>
                    <CardComponent src={players.secondPlayer.firstCard.value}/>
                    <CardComponent src={players.secondPlayer.secondCard.value}/>
                </PlayerHand>

                <PlayerHand>
                    <CardComponent src={players.thirdPlayer.firstCard.value}/>
                    <CardComponent src={players.thirdPlayer.firstCard.value}/>
                </PlayerHand>

                <PlayerHand>
                    <CardComponent src={players.fourthPlayer.firstCard.value}/>
                    <CardComponent src={players.fourthPlayer.firstCard.value}/>
                </PlayerHand>

                <PlayerHand>
                    <CardComponent src={players.fifthPlayer.firstCard.value}/>
                    <CardComponent src={players.fifthPlayer.firstCard.value}/>
                </PlayerHand>

                <PlayerHand>
                    <CardComponent src={players.sixthPlayer.firstCard.value}/>
                    <CardComponent src={players.sixthPlayer.firstCard.value}/>
                </PlayerHand>
            </div>

            <div className="submit-button">
                <button>Calculate</button>
            </div>
        </div>
    );
}

export default App;
