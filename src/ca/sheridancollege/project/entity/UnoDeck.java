package ca.sheridancollege.project.entity;

import ca.sheridancollege.project.Card;
import ca.sheridancollege.project.GroupOfCards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 *
 * This class +++Insert Description Here+++
 *
 * @author Hangge Tuo Created: Jun 28, 2020 Updated: Aug 10, 2020
 */
public class UnoDeck extends GroupOfCards {

    private UnoCard[] unoCards;
    private ArrayList<UnoCard> availablePile;
    private ArrayList<UnoCard> discardPile;
    private static UnoDeck deck = null;
//    private int cardsInDeck = 0;

    //main class for test
    public static void main(String[] args) {
        UnoDeck u1 = new UnoDeck(108);
        u1.initDeck();
        System.out.println("Before shuffle:");
        u1.showCards(u1.availablePile);
        System.out.println("After shuffle:");
        u1.shuffle();
        u1.showCards(u1.availablePile);
    }

    /**
     * UnoDeck constructor
     */
    private UnoDeck(int size) {
        super(size);
        unoCards = new UnoCard[108];
    }

    /**
     * The method is for initialing the game cards, build 108 cards and add to availablePile
     */
    public static UnoDeck getInstance() {
        if (deck == null) {
            deck = new UnoDeck(108);
        }
        return deck;
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
                unoCards[cardsInDeck++] = new UnoCard(color, value);
                if (j > 0) {
                    unoCards[cardsInDeck++] = new UnoCard(color, value);
                }
            }
        }

        //Add Wild Cards: Wild Wild and Wild DrawFour
        UnoCardValue[] values1 = new UnoCardValue[]{UnoCardValue.WILD, UnoCardValue.DRAWFOUR};
        for (UnoCardValue value : values1) {
            for (int i = 0; i < 4; i++) {
                unoCards[cardsInDeck++] = new UnoCard(UnoCardColor.WILD, value);
            }
        }
        //Add whole Uno cards to availablePile.
        availablePile = new ArrayList<>();
        discardPile = new ArrayList<>();
        for (UnoCard card : unoCards) {
            availablePile.add(card);
        }
        //Empty dicardPile
        discardPile.clear();
    }

    /**
     * Override shuffle method of parent class to add condition for starting game.
     */
    @Override
    public void shuffle() {
        if (availablePile.size() != 108) {
            try {
                throw new Exception("The number of cards should be 108.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        Collections.shuffle(availablePile);
    }

    public UnoCard getCard() {
        if (availablePile.size() == 0) {
            throw new IllegalArgumentException("Cannot draw a card since thiere are no cards in the deck");
        }
        return availablePile.remove(availablePile.size() - 1);
    }

    public boolean pushDiscardPile(UnoCard card) {
        return discardPile.add(card);
    }

    public int compareToCard(UnoCard[] cards) {
        int[] value = new int[cards.length];
        int maxIndex = 0;
        for (int i = 0; i < cards.length; i++) {
            value[i] = cards[i].getCardValue().ordinal() > 9 ? 0 : cards[i].getCardValue().ordinal();
        }
        for (int i = 0; i < value.length - 1; i++) {
            if (value[maxIndex] < value[i + 1]) {
                maxIndex = i + 1;
            }
        }
        return maxIndex;
    }

    /**
     * The method is for displaying all cards as strings
     *
     * @param cards The collection of CARDS that need to be displayed
     */
    private void showCards(ArrayList<UnoCard> cards) {
        int count = 0;
        for (int i = 0; i < cards.size(); i++) {
            System.out.println((i + 1) + ". " + cards.get(i).toString());
        }
    }

    public void showCards(UnoCard[] cards) {
        for (int i = 0; i < cards.length; i++) {
            System.out.println((i + 1) + ". " + cards[i].toString());
        }
    }
}
