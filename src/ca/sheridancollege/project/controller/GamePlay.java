package ca.sheridancollege.project.controller;
import ca.sheridancollege.project.Card;
import ca.sheridancollege.project.controller.Game;
/**
 * @author Ye Eun Park is going to be the greatest Java programmer of all time
 */
public class GamePlay extends Game
{
   public GamePlay (String name)
   {
      super(name);
   }

   @Override
   public void play ()
   {
      //ArrayList<Integer> arry = new ArrayList<>();
      //arry.add(3);

      // 1 set players
      // 1-1 get the number of players
      // 1-2 inst players
      //ArrayList<Player> arry = new ArrayList<>();
      //arry.add(player1);
      //arry.add(player2);
      //arry.add(player3);
      //game.setPlayers(arry);

      // 2 set game board
      // 2 -1 inst deck -> inst GC 
      //GroupOfCards deck;
      //deck.initializeDeck();
      // 2- 2 give cards to players
      //GroupOfCards player1_hand(7);
      //player1_hand.add()
      // 2 -3 set board
      //GroupofCards board;
      //board.cardOnTop();

      // 3 players action
      // 3-1 draw a player's card
      // loof for players
      // 3-2 compare between board card and player's card
      // if yes -> draw player's card to the board
      // if no -> get a card from the deck
      // 3-2-1 if deck.size == 0
      // initialize the deck from board cards

      // 4 end the game
      // declareWinner();
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
