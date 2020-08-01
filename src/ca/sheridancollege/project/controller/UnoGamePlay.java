package ca.sheridancollege.project.controller;
import ca.sheridancollege.project.Card;
import ca.sheridancollege.project.Player;
import ca.sheridancollege.project.entity.*;
import java.util.ArrayList;
/**
 * @author Ye Eun Park is going to be the greatest Java programmer of all time
 */
public class UnoGamePlay extends Game
{
   private ArrayList<Player> currentPlayers;
   //private UnoDeck deck;
   //private ArrayList<Card> playerHand;
   private boolean gameDirection; // true==clockwise, false==couterclockwise
   private Player currentPlayer;
   private int currentPlayerID;
   private UnoCardColor validColor;
   private UnoCardValue validValue;
   private static final UnoCardColor WILD = UnoCardColor.WILD;

   public table  = UnoTable.getInstance();

   public UnoGamePlay (String name)
   {
      super(name);
      currentPlayers = new ArrayList<Player>();
   }

   //public Game -> chk
   public void start ()
   {
      table.prepareTable();
      validColor = table.showTopCard().getCardColor();
      validValue = table.showTopCard().getCardValue();
      gameDirection = true;
      //currentPlayer = 
      play();
   }

   public UnoCard getTopCard ()
   {
      Unocard tmp;
      return tmp;
   }

   public Player getPreviousPlayer (int number)
   {
   }

   public ArrayList<Card> getPlayerHand (String id)
   {
   }

   public int getPlayerHandSize (String id)
   {
      return currentPlayers[id].playerHand.length;
   }

   public UnoCard getPlayerHandCard (String id, int choice)
   {
   }

   public boolean hasEmptyHand (String id)
   {
      return getPlayerHandSize(id) == 0 ? true : false;
   }

   public boolean isValidColor (UnoCard card)
   {
      return validColor == card.getCardColor();
   }

   public boolean isValidValue (UnoCard card)
   {
      return validValue == card.getCardValue();
   }

   public void showCardOptions (UnoCard topCard)
   {
      if (topCard.getCardColor() == WILD) {

      }
      else {
         for (UnoCard cardOnHand : currentPlayer.playerHand) {
            if (isValidColor(cardOnHand)) {
               System.out.println("Color Matched");
            }
            if (isValidValue(cardOnHand)) {
               System.out.println("Value Matched");
            }
         }
         //if no cards availble
         System.out.println("Draw a card");
         UnoCard newCard = table.pullCard();
         if (newCard.getCardColor() == WILD
             || isValidColor(newCard) || isValidValue(newCard)) {
            table.pushCard(newCard);
         }
         else {
            currentPlayer.playerHand.add(newCard);
         }
      }
   }

   public void setNextPlayer (int number)
   {
      if (gameDirection) {
         currentPlayer = currentPlayers[(currentPlayerID + number) % 4];
      }
      else {
         currentPlayer = currentPlayers[(currentPlayerID - number + 4) % 4];
      }
   }

   public void setCardColor (UnoCard card)
   {
      validColor = card.cardColor;
   }

   //TODO: check below 2 functions
   public void submitDraws (String id)
   {
   }

   public void submitPlayerCard ()
   {
   }

   public void sayUno ()
   {
   }

   public boolean askReplay ()
   {
   }

   @Override
   public void play ()
   {
      while (true) {
         currentPlayer
      }
   }

   @Override
   public void declareWinner ()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

//give player cards
   public void giveCards (Card topCard)
   {
   }
}
