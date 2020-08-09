package ca.sheridancollege.project.player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import ca.sheridancollege.project.Player;

/**
 * This is a subclass of Player.java
 *
 * @author Liya
 */
public class UnoPlayer extends Player {

    ArrayList<String> playerList = new ArrayList<>();
    protected int winTimes = 0;
    protected int loseTimes = 0;
    private boolean isDealer = false;

    public UnoPlayer(String name) {
        super(name);
    }

    @Override
    public void play() {

        //case 1:player name
        //case 1.1: the first player come in

        while (playerList.size() < 4) {

            if (!isPlayerExist(this.getName())) {
                System.out.println("successfully!  Your playername can be used.");
                playerList.add(this.getName());

            } else {
                System.out.println("Please enter your name as the unique ID: ");
                Scanner sc = new Scanner(System.in);
                this.setName(sc.nextLine());
            }
        }
        if (playerList.size() >= 4) {
            System.out.println("Now the players are " + playerList.size());
        }

        //show the playerList        
        System.out.println("Players names are: ");
        for (int i = 0; i < playerList.size(); i++) {
            System.out.print(playerList.get(i) + "\t");
        }
        System.out.println();

        System.out.println("Welcome to UNO game! Start now(Y/N)?");
        Scanner s = new Scanner(System.in);
        if (s.next().equalsIgnoreCase("Y")) {

            Random random = new Random();
            int r = random.nextInt(playerList.size());

            System.out.println(playerList.get(r) + " is the Dealer");
            
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean isPlayerExist(String name) {
        boolean isExist = true;
        if (playerList.lastIndexOf(name) == -1) {
//         System.out.println("Your player name can be used.");// Returning -1 means that no same name exist
            isExist = false;
        }
        return isExist; // return true means the name already exist
    }

    public int getWinTimes() {
        return winTimes;
    }

    public void setWinTimes(int winTimes) {
        this.winTimes = winTimes;
    }

    public int getLoseTimes() {
        return loseTimes;
    }

    public void setLoseTimes(int loseTimes) {
        this.loseTimes = loseTimes;
    }

    public boolean isIsDealer() {
        return isDealer;
    }

    public void setIsDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }

}
