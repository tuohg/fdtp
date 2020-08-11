package ca.sheridancollege.project.player;

import java.util.ArrayList;
import ca.sheridancollege.project.Player;
import ca.sheridancollege.project.entity.UnoCard;

/**
 * This is a subclass of Player.java
 *
 * @author Liya
 * @review Hangge Tuo
 */
public class UnoPlayer extends Player {

//    ArrayList<String> playerList = new ArrayList<>();
    protected int winTimes = 0;
    protected int loseTimes = 0;
    private boolean isDealer = false;
    protected ArrayList<UnoCard> playerHand = new ArrayList<>();

    public UnoPlayer(String name) {
        super(name);
    }

    @Override
    public void play() {

    }

    public ArrayList<UnoCard> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(ArrayList<UnoCard> playerHand) {
        this.playerHand = playerHand;
    }

    public void addPlayerHand(UnoCard card) {
        playerHand.add(card);
    }

    public UnoCard popPlayerHand(int index) {
        return playerHand.remove(index);
    }

    public int getPlayerHandSize() {
        //TODO: to be implemented with playerManager and playerHandList
        return playerHand.size();
    }

    public boolean hasEmptyHand() {
        return playerHand.size() == 0 ? true : false;
    }


    public int getWinTimes() {
        return winTimes;
    }

    public void setWinTimes(int winTimes) {
        this.winTimes = winTimes;
    }
    
    public void setWinTimes(){
        this.winTimes++;
    }

    public int getLoseTimes() {
        return loseTimes;
    }

    public void setLoseTimes(int loseTimes) {
        this.loseTimes = loseTimes;
    }
    
    public void setLoseTimes(){
        this.loseTimes++;
    }

    public boolean isIsDealer() {
        return isDealer;
    }

    public void setIsDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }
    
    public String toString(int order){
        String strOrder = "";
        if (order >= 0) {
            strOrder +=(order+1);
        }
        String strPlayer = "Player "+strOrder+": "+this.getName();
        if (isDealer) {
            strPlayer +="(Dealer)";
        }
        return strPlayer;
    }
    
    public String toString(){
        return this.toString(-1);
    }

}
