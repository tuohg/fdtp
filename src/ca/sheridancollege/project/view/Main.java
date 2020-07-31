package ca.sheridancollege.project.view;

import ca.sheridancollege.project.*;
import ca.sheridancollege.project.entity.*;

/**
 * This class is the main class.
 *
 * @author Hanan Fadel July 2020
 *
 */
public class Main {

    public static void main(String[] args) {
        Interpreter inter = new Interpreter();
        SignUpPlayers signup = new SignUpPlayers();
        UnOGamePlay game = new UnoGamePlay();
        
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t\t\t\tUNO CARD GAME");
        System.out.println("\nUNO RULES:\n");
        System.out.println("TO PLAY A CARD, WRITE PLAY FOLLOWED BY THE CARD NAME AND PRESS ENTER\n");
        System.out.println("IF YOU DO NOT HAVE ANY MATCHED CARD, WRITE DRAW AND PRESS ENTER\n");
        System.out.println("AFTER DRAWING A CARD, IF YOU STILL DO NOT HAVE A MATCHED CAED,  "
                + "WRITE PASS TO PASS THE TURN TO NEXT PLAYER AND PRESS ENTER");
        System.out.println("--------------------------------------------------------------------------------");

        // Initiate the game
        game.init();

        // Register the players
        while (signup.sign() == false);

        // Start the game
        game.start();

        // Read the usee input 
        inter.readCommands();

        // End the game.
        game.finish();

    }

}
