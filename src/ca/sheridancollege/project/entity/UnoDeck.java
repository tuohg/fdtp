package ca.sheridancollege.project.entity;

import ca.sheridancollege.project.Card;
import ca.sheridancollege.project.GroupOfCards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * This class +++Insert Description Here+++
 *
 * @author Hangge Tuo
 * Created: Jun 28, 2020
 * Updated: Jul 30, 2020
 */

public class UnoDeck extends GroupOfCards
{
   private UnoCard[] unoCards;
   private ArrayList<Card> availablePile;
   private ArrayList<Card> discardPile;

   //main class for test
   public static void main (String[] args)
   {
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
   public UnoDeck (int size)
   {
      super(size);
      unoCards = new UnoCard[108];
   }

   /**
    * The method is for initialing the game cards, build 108 cards and add to availablePile
    */
   public void initDeck ()
   {
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
      UnoCardValue[] values1 = new UnoCardValue[] {UnoCardValue.WILD, UnoCardValue.DRAWFOUR};
      for (UnoCardValue value : values1) {
         for (int i = 0; i < 4; i++) {
            unoCards[cardsInDeck++] = new UnoCard(UnoCardColor.WILD, value);
         }
      }
      //Add whole Uno cards to availablePile.
      availablePile = new ArrayList<Card>();
      discardPile = new ArrayList<Card>();
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
   public void shuffle ()
   {
      if (availablePile.size() != 108) {
         try {
            throw new Exception("The number of cards should be 108.");
         }
         catch (Exception ex) {
            Logger.getLogger(UnoDeck.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      Collections.shuffle(availablePile);
   }

   /**
    * The method is for displaying all cards as strings
    *
    * @param cards The collection of CARDS that need to be displayed
    */
   private void showCards (ArrayList<Card> cards)
   {
      int count = 0;
      for (int i = 0; i < cards.size(); i++) {
         System.out.println(++count + " " + cards.get(i).toString());
      }
   }

}
