package ca.sheridancollege.project.view;

import ca.sheridancollege.project.*;
import ca.sheridancollege.project.entity.*;
import java.util.ArrayList;

/**
 * This class models the abstract interface for UnoTable
 *
 * @author Hanan Fadel July 2020
 * @review Hangge Tuo Aug 2020
 */
public interface Tabel {

    public abstract void showWelcome();
    
    public abstract int showMainMenu();
    /**
     * Prepares the table to start the game.
     */
    public abstract void prepareTable();

}
