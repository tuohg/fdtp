package ca.sheridancollege.project.player;


import ca.sheridancollege.project.Player;
import ca.sheridancollege.project.UnoCard;
/**
 * This is a subclass of Player.java
 *
 * @author  Liya Ding
 */
public class UnoPlayer extends Player {
	private int playerId;
	private Hand p_hand;
	private String p_name;

	public UnoPlayer(String name, int playerId) {
		this.playerId   = playerId;
		this.p_hand = new Hand();
		this.p_name = name;
	}        
    @Override
    public void play() {
      
    }
	/**
	 * Get the Identification (ID) of the player.
	 * @return the player's ID.
	 */
	public int getPlayerId(){
		return this.playerId;
	}
	
	/**
	 * Get the name of the player.
	 * @return the player's name.
	 */
	public String getName(){
		return this.p_name;
	}
	
	/**
	 * @return the number of cards present in hand
	 */
	public int numCards(){
		return p_hand.getNumCards();
	}
	
	/**
	 * The player takes a card if it's its turn.
	 * @param card that will be taken.
	 */
	public boolean takeCard(UnoCard card){
		return this.p_hand.addCard(card);
	}
	
	/**
	 * Play a card if it is its turn.
	 * @param cardName: The name of the card. The name is compose by the color 
	 * and the number or effect.
	 * @return the card.
	 */
	public UnoCard playCard(String cardName){
		int i = 0;
		while(i != this.p_hand.getNumCards()){
			if(this.p_hand.showCard(i).equals(cardName))
				return this.p_hand.getCard(i);
			i++;
		}
		
		return null;
	}
	
	/**
	 * Play the ith card in the hand of the player; 
	 * @param i index of the card.
	 * @return the ith card or null if there is no ith card.
	 */
	public Card playCard(int i){
		return this.p_hand.getCard(i);
	}
	
	/**
	 * Print all the cards on the hand of the player.
	 */
	public void showCards(){
		int ncards =  this.p_hand.getNumCards();
		String toPrint = "";
		int i;
		if(ncards == 0){
			System.out.print("EMPTY HAND.");
			
		} else{
			for(i = 0; i < ncards; i++){
				toPrint += "[" + this.p_hand.showCard(i) + "] ";
			}
		}
		
		if(toPrint.length() > 80){
			for(i = 0; i < toPrint.length()/80; i++)
				System.out.println(toPrint.substring(i*80, i*80 + 80));
			System.out.println(toPrint.substring(i*80));
		} else {
			System.out.println(toPrint);
		}
	}
	
	/**
	 * Empty the hand.
	 */
	public void emptyHand(){
		while(!this.p_hand.isEmpty())
			this.p_hand.getCard(0);
		}
    
}
