package ca.sheridancollege.project.view;

import ca.sheridancollege.project.*;
import ca.sheridancollege.project.entity.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class sign up the players.
 *
 * @author Hanan Fadel July 2020
 *
 */
public class SignUpPlayers {

    private Scanner input = new Scanner(System.in);
    private PlayersManager pM = new PlayersManager();
    private int idGen = 0;

    public boolean sign() {
        int number;
        System.out.print("ENTER THE NUMBER OF PLAYERS (2 TO 4): ");

        try {
            number = input.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("PLEASE ENTER  A NUMBER.");
            input.next();
            return false;
        }

        while (number < 2 || number > 4) {
            System.out.println("GAME IS FROM 2 TO 4 PLAYERS");
            System.out.print("ENTER THE NUMBER OF PLAYER (2 TO 4): ");
            number = input.nextInt();
        }

        for (int i = 0; i < number; i++) {
            System.out.print("PLAYER " + String.valueOf(i + 1) + ": ");
            pM.addPlayer(new Player(input.next(), idGen++));
        }

        return true;
    }
}
