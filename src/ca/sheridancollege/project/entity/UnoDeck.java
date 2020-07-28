package ca.sheridancollege.project.entity;

import ca.sheridancollege.project.Card;
import java.util.ArrayList;

/**
 *
 * This class +++Insert Description Here+++
 * @author Hangge Tuo
 * Created: Jun 28, 2020
 * Updated: Jul 28, 2020
 */

public class UnoDeck {
    private UnoCard[] cards;
    private ArrayList<Card> availablePile;
    private ArrayList<Card> discardPile;
    
    //main class for test
    public static void main(String[] args) {
        UnoDeck u1 = new UnoDeck();
        u1.initDeck();
        int count =0;
        for (int i = 0; i < u1.cards.length; i++) {
            System.out.println(++count + " " +u1.cards[i].toString());
        }
    }
    
    /**
     * UnoDeck constructor
     */
    public UnoDeck() {
        cards = new UnoCard[108];
    }
    
    public void initDeck() {
        UnoCardColor[] colors = UnoCardColor.values();
        UnoCardValue[] values = UnoCardValue.values();
        int cardsInDeck = 0;
        
        //Add all non-Wild Cards
        for (int i = 0; i < colors.length - 1; i++) {
            UnoCardColor color = colors[i];
            for (int j = 0; j < 13; j++) {
                UnoCardValue value = values[j];
                cards[cardsInDeck++] = new UnoCard(color, value);
                if (j>0) {
                    cards[cardsInDeck++] = new UnoCard(color, value);
                }
            }
        }
        
        //Add Wild Cards: Wild Wild and Wild DrawFour
        UnoCardValue[] values1 = new UnoCardValue[]{UnoCardValue.WILD, UnoCardValue.DRAWFOUR};
        for (UnoCardValue value : values1) {
            for (int i = 0; i < 4; i++) {
                cards[cardsInDeck++] = new UnoCard(UnoCardColor.WILD, value);
            }
        }
        //Add whole Uno cards to availablePile.
        for (UnoCard card : cards) {
            availablePile.add(card);
        }
        //Empty dicardPile
        discardPile.clear();
    }
}
