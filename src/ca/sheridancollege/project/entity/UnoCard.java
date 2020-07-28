package ca.sheridancollege.project.entity;

import ca.sheridancollege.project.Card;

/**
 *
 * This class defines a specific Uno card.
 * @author Hangge Tuo
 * Created: Jul 28, 2020
 * Updated: Jul 28, 2020
 */
public class UnoCard extends Card{
    private UnoCardColor cardColor;
    private UnoCardValue cardValue;
//    private UnoCardState cardState;

    public UnoCard(UnoCardColor cardColor, UnoCardValue cardValue) {
        this.cardColor = cardColor;
        this.cardValue = cardValue;
//        this.cardState = cardState;
    }
    
    
    
    @Override
    public String toString() {
        return this.cardColor+"-"+this.cardValue;
    }

}
