package ca.sheridancollege.project.controller;
import ca.sheridancollege.project.Card;
import ca.sheridancollege.project.PlayersManager;
import ca.sheridancollege.project.entity.*;
import ca.sheridancollege.project.player.Player;
import java.util.*;

/**
 * @author Ye Eun Park is going to be the greatest Java programmer of all time
 */
public class UnoGamePlay extends Game
{
   private boolean gameDirection; // true==clockwise, false==couterclockwise
   private UnoPlayer currentPlayer;
   private UnoCard topCard;

   private UnoCardColor validColor;
   private UnoCardValue validValue;
   private static final UnoCardColor WILD = UnoCardColor.WILD;

   public table  = UnoTable.getInstance();
   private pM  = PlayersManager.getInstance();
   private int totalPlayer = pM.totalPlayer;

   private static UnoGamePlay unoGame = null;
   private final static int N_CARDS_INI = 7;

   public UnoGamePlay (String name)
   {
      super(name);
   }

   public static UnoGamePlay getInstance ()
   {
      if (unoGame == null) {
         unoGame = new UnoGamePlay("Uno Game");
      }
      return unoGame;
   }

   // UseCase #1 init table
   public void init ()
   {
      table.prepareTable();
   }

   // UseCase #2 signUp players
   public void signUpPlayers ()
   {
      SignUpPlayers.sign();
   }

   // UseCase #4 setDealer
   public void setDealer ()
   {
      int max = 0;
      for (Player player : pM.currentPlayers) {
         UnoCard card = table.pullCard();
         UnoCardValue cardValue = card.getCardValue();
         int value = cardValue.ordinal() > 9 ? 0 : cardValue.ordinal();
         if (value > max) {
            currentPlayer = player; // Now currentPlayer is the dealer
         }
         // UseCase #5 discard the card
         table.pushCard(card);
      }
   }

   // UseCase #6 distribute 7card to players in order of dealer-first
   public void distributeCard ()
   {
      for (int i = 0; i < pM.totalPlayer; i++) {
         int index = (i + currentPlayer.id) % totalPlayer;
         for (int j = 0; j < N_CARDS_INI; j++) {
            pM.currentPlayers[index].getCard(table.pullCard());
         }
      }
   }

   // UseCase #7 draw the top card to start the game
   // UseCase #8 determines the first player to play, who is the player ID behind the dealer
   public void drawTopCard ()
   {
      topCard = table.pullCard();
      currentPlayer = pM.currentPlayers[(currentPlayer.id++) % totalPlayer];
   }

   @Override
   public void play ()
   {
      //TODO: to be implemented with VIEW
   }

   @Override
   public void declareWinner ()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public UnoCard getTopCard ()
   {
      return topCard;
   }

   public Player getPreviousPlayer ()
   {
      return pM.currentPlayers[(currentPlayer.id - 1) % totalPlayer];
   }

   public ArrayList<Card> getPlayerHand (String id)
   {
      //TODO: to be implemented with playerManager and playerHandList
      return pM.currentPlayers[id].playerHand;
   }

   public int getPlayerHandSize (String id)
   {
      //TODO: to be implemented with playerManager and playerHandList
      return pM.currentPlayers[id].playerHand.length;
   }

   public UnoCard getPlayerHandCard (String id, int choice)
   {
      //TODO: to be implemented with playerManager and playerHandList
      return pM.currentPlayers[id].playerHand[choice];
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
      for (int i = 0; i < currentPlayer.playerHand.length; i++) {
         if (isValidColor(cacurrentPlayer.playerHand[i])) {
            System.out.println("#" + (i + 1) + " Color Matched");
         }
         if (isValidValue(cacurrentPlayer.playerHand[i])) {
            System.out.println("#" + (i + 1) + "Value Matched");
         }
      }
   }

   public void showDrawOptions ()
   {
      System.out.println("Draw a card");
      UnoCard newCard = table.pullCard();
      if (newCard.getCardColor() == WILD) {
         System.out.println("You've drew WILD card!");
         table.pushCard(newCard);
      }
      else if (isValidColor(newCard) || isValidValue(newCard)) {
         System.out.println("You've drew matched card!");
         table.pushCard(newCard);
      }
      else {
         System.out.println("You've got one more card in you hand");
         currentPlayer.playerHand.add(newCard);
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
      validColor = card.getCardColor();
   }

   public boolean askReplay ()
   {
      Scanner src = new Scanner(System.in);
      String output = src.nextLine();
      System.out.println("Replay? y/n");
      return "y".equals(output);
   }
}
