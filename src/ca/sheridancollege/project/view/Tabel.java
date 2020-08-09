package ca.sheridancollege.project.view;

import ca.sheridancollege.project.*;
import ca.sheridancollege.project.entity.*;
import java.util.ArrayList;

/**
 * This class models the abstract interface for UnoTable
 *
 * @author Hanan Fadel July 2020
 */
public abstract interface Tabel {

    /**
     * Prepares the table to start the game.
     */
    public abstract void prepareTable();

    /**
     * Get the last played card.
     */
    public abstract Card showTopCard();

    public abstract boolean pushCard(UnoCard card);

    /**
     * Take a card from the top of the deck. 
     * @return one card.
     */
    public abstract UnoCard pullCard();

    public abstract int getNumCardsOnDeck();

}
