package ca.sheridancollege.project.view;

import ca.sheridancollege.project.*;
import ca.sheridancollege.project.controller.UnoGamePlay;
import ca.sheridancollege.project.entity.*;
import ca.sheridancollege.project.player.UnoPlayer;

/**
 * This class represents a game of UNO. It includes the discard pile, the deck, the players and their hands and the game
 * status.
 *
 * @author Hanan Fadel July 2020
 * @review Hangge Tuo Aug 2020
 *
 */
public class UnoTable implements Tabel {

    private UnoPlayer[] player;
    private UnoGamePlay play;
    private static UnoTable table = null;
    boolean isWin = false;

    private UnoTable() {
        play = UnoGamePlay.getInstance();
    }

    public static void main(String[] args) {
        UnoTable table = getInstance();
        table.showWelcome();
        while (true) {
            switch (table.showMainMenu()) {
                case 1:
                    table.showPlayerRegister();
                    showDivider();
                    break;
                case 2:
                    if (table.player.length < 3) {
                        System.out.println("The players is under 3, please register the user first.");
                        continue;
                    }
                    table.prepareTable();
                    showDivider();
                    if (!showIsContinue()) {
                        continue;
                    }
                    table.showDrawFirstCard();
                    showDivider();
                    if (!showIsContinue()) {
                        continue;
                    }
                    table.showDrawTopCard();
                    showDivider();
                    if (!showIsContinue()) {
                        continue;
                    }
                    table.showDistributeCard();
                    showDivider();
                    if (!showIsContinue()) {
                        continue;
                    }
                    table.showPlay();
                    break;
                case 3:
                    table.showBillboard();
                    break;
                case 4:
                    showExit();
                    break;
                default:
                    throw new AssertionError();
            }
        }

    }

    public static UnoTable getInstance() {
        if (table == null) {
            table = new UnoTable();
        }
        return table;
    }

    /**
     * Prepares the table to start the game. Any remaining cards in the Discard Pile are put back into the deck
     */
    public void prepareTable() {

        play.init();
        System.out.println("The shuffle is complete and you are now ready to play.");
    }

    @Override
    public void showWelcome() {
        showDivider();
        System.out.println("\t\t\t\tUNO CARD GAME");
        System.out.println("\nUNO RULES:\n");
        System.out.println("TO PLAY A CARD, WRITE PLAY FOLLOWED BY THE CARD NAME AND PRESS ENTER\n");
        System.out.println("IF YOU DO NOT HAVE ANY MATCHED CARD, WRITE DRAW AND PRESS ENTER\n");
        System.out.println("AFTER DRAWING A CARD, IF YOU STILL DO NOT HAVE A MATCHED CAED,  "
                + "WRITE PASS TO PASS THE TURN TO NEXT PLAYER AND PRESS ENTER");
        showDivider();
    }

    @Override
    public int showMainMenu() {
        showDivider();
        System.out.println("****************** Main Menu ******************");
        System.out.println("1. Register player");
        System.out.println("2. New game");
        System.out.println("3. Billboard");
        System.out.println("4. Exit");
        System.out.println("***********************************************");
        int in = Input.getInt("Please choose menu option:", 1, 3);
        showDivider();
        return in;
    }

    public void showPlayerRegister() {
        int numOfPlayer =0;
        boolean isReturn = true;
        while (isReturn) {
            switch (showRegisterMenu()) {
                case 1:
                    if (player != null) {
                        String prompt = "There are "+player.length+" players now. If yes, they are cleared. Still continue?(y/n)";
                        if(!showIsContinue(prompt)) continue;
                    }
                    numOfPlayer = Input.getInt("How many players play the game(number between 3 and 8)", 3, 8);
                    registerPlayers(false, numOfPlayer);
                    isReturn=false;
                    break;
                case 2:
                    if (player == null) {
                        System.out.println("Error: No player registered yet, please register first.");
                    } else if (player.length >=8) {
                        System.out.println("Error: The number of registered players has reached maximum - 8.");
                    }else{
                        String prompt ="There are "+ player.length +" players now, maximum 8.\nHow many players you want to add:";
                        numOfPlayer = Input.getInt(prompt, 3-player.length, 8-player.length);
                        registerPlayers(true, numOfPlayer);
                    }
                    break;
                case 3:
                    isReturn=false;
                    break;
                default:
                    throw new AssertionError();
            }
            
        }
    }

    public int showRegisterMenu() {
        System.out.println("****************** Register Menu ******************");
        System.out.println("1. New players");
        System.out.println("2. Add players");
        System.out.println("3. Return main menu");
        System.out.println("***************************************************");
        int in = Input.getInt("Please choose menu option:", 1, 3);
        return in;
    }
    
    public void registerPlayers(boolean isAdd, int numOfPlayer){
        if (isAdd) {
            numOfPlayer += player.length;
        }
        UnoPlayer[] tmpPlayer = new UnoPlayer[numOfPlayer];
        for (int i = 0; i < numOfPlayer; i++) {
            if (isAdd && i<player.length) {
                tmpPlayer[i] = player[i];
            }else{
                System.out.print("Please enter the No." + (i + 1) + " player's name.");
                String name = Input.getString("(length between 3 and 8)", 3, 8);
                tmpPlayer[i] = new UnoPlayer(name);
            }
        }
        player = tmpPlayer;
        System.out.println("Registration is complete, return main menu.");
    }

    public void showBillboard() {
        showDivider();
        System.out.println("The function of 'BILLBOARD' is coming soon!!!");
        showDivider();
    }

    public void showPlayerHand(UnoPlayer player) {
        System.out.println(player.getName() + "'s hand:");
        for (int i = 0; i < player.getPlayerHand().size(); i++) {
            System.out.println((i + 1) + ". " + player.getPlayerHand().get(i).toString());
        }
    }

    public void showDrawFirstCard() {
        System.out.println("Each player draws one card:");
        int numDearler = play.setDealer(player, player.length);
        play.setCurrentPlayerIndex(numDearler, player.length);
        player[numDearler].setIsDealer(true);
        System.out.println("The dealer is " + player[numDearler].toString());
        play.setNextPlayerIndex(player.length);
    }

    public void showDrawTopCard() {
        System.out.println("Draw top card now:");
        play.drawTopCard();
        showTopCard();
    }

    public void showTopCard() {
        System.out.println("The top card is " + play.getTopCard().toString());
        System.out.println("The valid color is " + play.getValidColor());
    }

    public void showDistributeCard() {
        System.out.println("Now let's distribute the CARDS, each gets 7 cards:");
        for (int i = 0; i < player.length; i++) {
            play.distributeCard(player[i], 7);
            showPlayerHand(player[i]);
        }
    }

    public void showPlay() {
        UnoPlayer currentPlayer = player[play.getCurrentPlayerIndex()];
        String prompt;
        System.out.println("Now let's play the CARDS:");
        while (!isWin) {
            showDivider();
            showTopCard();
            currentPlayer = player[play.getCurrentPlayerIndex()];
            showCurrentPlayer();
            showPlayerHand(currentPlayer);

            if (play.isValidCards(currentPlayer)) {
                if (!showChooseCards(currentPlayer)) {
                    continue;
                }
            } else {
                showDrawCards(currentPlayer);
            }

            showChooseValidColor();

            if (play.judgeIsWin(currentPlayer)) {
                isWin = true;
                showWin(currentPlayer);
                break;
            }

            play.setNextPlayerIndex(player.length);
            currentPlayer = player[play.getCurrentPlayerIndex()];
            showCurrentPlayer();
            showDivider();
            play.dealWithWildCard(currentPlayer, player.length);
            showCurrentPlayer();
            showIsContinue("Continue?(y/n)");
            showDivider();
        }
    }

    public boolean showChooseCards(UnoPlayer player) {
        String prompt = "Please choose the CARDS you are going to play.";
        int playerChoice = Input.getInt(prompt, 1, player.getPlayerHandSize());
        if (play.isValidCard(player.getPlayerHand().get(playerChoice - 1))) {
            play.setTopCard(player.popPlayerHand(playerChoice - 1));
            play.setValidColor(play.getTopCard());
            System.out.println("Successfully played.");
            return true;
        } else {
            System.out.println("The card you have chosen is not valid");
            return false;
        }
    }

    public void showDrawCards(UnoPlayer player) {
        System.out.println("Since you don't have valid cards, you are drawed one card.");
        play.dealWithDrawCard(player, 1);
    }

    public static boolean showIsContinue(String prompt) {
        String in = Input.getString(prompt, new String[]{"Y", "y", "N", "n", ""});
        if ("N".equals(in) || "n".equals(in)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean showIsContinue() {
        return showIsContinue("Whether or not to continue?(y/n)");
    }

    public void showWin(UnoPlayer player) {
        System.out.println("Finally, " + player.toString() + " wins. Congratuation!!!");
        showIsContinue("Whether to return to the main menu(y/n)");
    }

    public void showGameOver() {
        System.out.println("Game Over");
    }

    public static void showExit() {
        System.out.println("System exit.");
        System.exit(0);
    }

    public void showOneCard(Card card) {
        System.out.println("Your hand is " + card.toString());
    }

    private void showColorOption() {
        UnoCardColor[] color = new UnoCardColor[4];
        System.out.println("Please choose:");
        for (int i = 0; i < color.length; i++) {
            color[i] = UnoCardColor.values()[i];
            System.out.println((i + 1) + ". " + color[i]);
        }
    }

    private void showChooseValidColor() {
        int playerChoice;
        if (play.getTopCard().getCardColor() == UnoCardColor.WILD) {
            System.out.println("Since you played a WILD CARD, you need to specify the valid color");
            showColorOption();
            playerChoice = Input.getInt("Please choose(1-4):", 1, 4);
            play.setValidColor(UnoCardColor.values()[playerChoice - 1]);
            System.out.println("Set valid color successfully!! The valid color is " + play.getValidColor());
        }
    }

    private void showCurrentPlayer() {
        int index = play.getCurrentPlayerIndex();
        System.out.println("The current player is " + player[index].toString(index));
    }

    private static void showDivider() {
        System.out.println("-----------------------------------------------------------------------");
    }

}
