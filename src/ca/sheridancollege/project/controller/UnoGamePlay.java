package ca.sheridancollege.project.controller;
import ca.sheridancollege.project.Card;
import ca.sheridancollege.project.Player;
import ca.sheridancollege.project.entity.*;
import java.util.*;

/**
 * @author Ye Eun Park is going to be the greatest Java programmer of all time
 */
public class UnoGamePlay extends Game
{
   //private ArrayList<Player> currentPlayers;
   //private UnoDeck deck;
   //private ArrayList<Card> playerHand;
   private boolean gameDirection; // true==clockwise, false==couterclockwise
   private Player currentPlayer;
   private int currentPlayerID;
   private UnoCard topCard;

   private UnoCardColor validColor;
   private UnoCardValue validValue;
   private static final UnoCardColor WILD = UnoCardColor.WILD;

   public table  = UnoTable.getInstance();
   private pM  = PlayersManager.getInstance();
   private int totalPlayer = pM.totalPlayer;

   public UnoGamePlay (String name)
   {
      super(name);
      currentPlayers = new ArrayList<Player>();
   }

   public void init ()
   {
      //1,2
      table.prepareTable();
      SignUpPlayers.sign();
      //3

      //4
      int max = 0;
      for (Player player : pM.currentPlayers) {
         UnoCard card = table.pullCard();
         UnoCardValue cardValue = card.getCardValue();
         int value = cardValue.ordinal() > 9 ? 0 : cardValue.ordinal();
         if (value > max) {
            currentPlayer = player; //currentPlayer == dealer
         }
         //5 
         table.pushCard(card);
      }

      //6
      for (int i = 0; i < pM.totalPlayer; i++) {
         int index = (i + currentPlayer.id) % totalPlayer;
         for (int j = 0; j < 7; j++) {
            pM.currentPlayers[index].getCard(table.pullCard());
         }
      }

      //7
      topCard = table.pullCard();
      //8
      currentPlayer = pM.currentPlayers[(currentPlayer.id++) % totalPlayer];
   }

   @Override
   public void play ()
   {
      while (true) {
         //valid color

         //valid value(0-9)

         //wildcard

         //9-1


         //9-2
         //player submit wild card
      }
   }

   @Override
   public void declareWinner ()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public UnoCard getTopCard ()
   {
      return table.pullCard();
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
         currentPlayer = pM.currentPlayers[(currentPlayerID + number) % totalPlayer];
      }
      else {
         currentPlayer = pM.currentPlayers[(currentPlayerID - number + totalPlayer) % totalPlayer];
      }
   }

   public void setCardColor (UnoCard card)
   {
      validColor = card.cardColor;
   }

   public boolean askReplay ()
   {
      Scanner src = new Scanner(System.in);
      String output = src.nextLine();
      System.out.println("Replay? y/n");
      return "y".equals(output);
   }
}
