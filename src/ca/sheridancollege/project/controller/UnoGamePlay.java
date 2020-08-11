package ca.sheridancollege.project.controller;

import ca.sheridancollege.project.Game;
import ca.sheridancollege.project.entity.*;
import ca.sheridancollege.project.player.*;

/**
 * @author Ye Eun Park is going to be the greatest Java programmer of all time
 * @review Hangge Tuo
 */
public class UnoGamePlay extends Game {

    private boolean gameDirection = true; // true==clockwise, false==couterclockwise
    private UnoCard topCard;
    private UnoDeck deck;
    private int currentPlayerIndex;

    private UnoCardColor validColor;
    private static final UnoCardColor WILD = UnoCardColor.WILD;
    private static UnoGamePlay unoGame = null;
    private final static int N_CARDS_INI = 7;

    private UnoGamePlay(String name) {
        super(name);
        deck = UnoDeck.getInstance();
    }

    public static UnoGamePlay getInstance() {
        if (unoGame == null) {
            unoGame = new UnoGamePlay("Uno Game");
        }
        return unoGame;
    }
    // UseCase #1 init table

    public void init() {
        deck.initDeck();
        deck.shuffle();
    }

//    UseCase #4 setDealer
    public int setDealer(UnoPlayer[] players, int playerNum) {
        UnoCard[] cards = new UnoCard[playerNum];
        for (int i = 0; i < playerNum; i++) {
            cards[i] = deck.getCard();
            System.out.println(players[i].toString(i) + "'s first card is " + cards[i].toString());
            deck.pushDiscardPile(cards[i]);
        }
        return deck.compareToCard(cards);
    }

    // UseCase #6 distribute 7card to players in order of dealer-first
    public UnoCard[] distributeCard(UnoPlayer player, int num) {
        UnoCard[] cards = new UnoCard[num];
        for (int i = 0; i < num; i++) {
            cards[i] = deck.getCard();
            player.addPlayerHand(cards[i]);
        }
        return cards;
    }

    @Override
    public void play() {
        //TODO: to be implemented with VIEW
    }

    @Override
    public void declareWinner() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

// UseCase #7 draw the top card to start the game
// UseCase #8 determines the first player to play, who is the player ID behind the dealer
    public UnoCard drawTopCard() {
        while (true) {
            setTopCard(deck.getCard());
            setValidColor(topCard);
            deck.pushDiscardPile(topCard);
            if (topCard.getCardValue().ordinal() <= 9) {
                System.out.println(topCard.toString());
                return topCard;
            }
        }
    }

    public boolean judgeIsWin(UnoPlayer player) {
        if (player.getPlayerHand().isEmpty()) {
            return true;
        } else if (player.getPlayerHand().size() <= 2) {
            System.out.println("\nsay UNOoooooooooooooooooooooooooooooooo!!!\n");
            return false;
        } else {
            return false;
        }
    }

    public boolean isValidCard(UnoCard card) {
        if (card.getCardColor() == UnoCardColor.WILD) {
            return true;
        } else if (topCard.getCardColor() == UnoCardColor.WILD) {
            return card.getCardColor() == validColor;
        } else {
            return (topCard.getCardColor() == card.getCardColor() || topCard.getCardValue() == card.getCardValue());
        }
    }

    public boolean isValidCards(UnoPlayer player) {
        for (UnoCard card : player.getPlayerHand()) {
            if (isValidCard(card)) {
                return true;
            }
        }
        return false;
    }


    public void setNextPlayerIndex(int playerNum) {
        if (gameDirection) {
            if (++currentPlayerIndex == playerNum) {
                currentPlayerIndex = 0;
            }
        } else {
            if (--currentPlayerIndex == -1) {
                currentPlayerIndex = playerNum - 1;
            }
        }
    }

    public void reverse() {
        gameDirection = !gameDirection;
    }

    public void dealWithWildCard(UnoPlayer player, int playerNum) {
        if (null != topCard.getCardValue()) {
            switch (topCard.getCardValue()) {
                case DRAWFOUR:
                    System.out.println("Since the last player played WILD card, you need to draw four CARDS. Here are four new CARDS");
                    dealWithDrawCard(player,4);
                    break;
                case DRAWTWO:
                    System.out.println("Since the last player played WILD card, you need to draw two CARDS. Here are your two new CARDS");
                    dealWithDrawCard(player,2);
                    break;
                case REVERSE:
                    reverse();
                    setNextPlayerIndex(playerNum);
                    setNextPlayerIndex(playerNum);
                    System.out.println("Reverse successfully!!!");
                    break;
                case SKIP:
                    setNextPlayerIndex(playerNum);
                    System.out.println("Skip successfully!!!");
                    break;
                default:
                    break;
            }
        }
    }
    
    public void dealWithDrawCard(UnoPlayer player, int num){
        deck.showCards(distributeCard(player, num));
    }

    public UnoCardColor getValidColor() {
        return validColor;
    }

    public void setValidColor(UnoCardColor validColor) {
        this.validColor = validColor;
    }

    public boolean setValidColor(UnoCard card) {
        if (card.getCardColor() == WILD) {
            return false;
        } else {
            this.setValidColor(card.getCardColor());
            return true;
        }
    }

//    public void setCardColor(UnoCard card) {
//        validColor = card.getCardColor();
//    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex, int playerNum) {
        if (currentPlayerIndex < 0) {
            this.currentPlayerIndex = playerNum - 1;
        } else if (currentPlayerIndex >= playerNum) {
            this.currentPlayerIndex = 0;
        } else {
            this.currentPlayerIndex = currentPlayerIndex;
        }
    }

    public UnoCard getTopCard() {
        return topCard;
    }

    public void setTopCard(UnoCard topCard) {
        this.topCard = topCard;
    }

    public boolean isGameDirection() {
        return gameDirection;
    }

    public void setGameDirection(boolean gameDirection) {
        this.gameDirection = gameDirection;
    }

}
