package ca.sheridancollege.project.view;

import java.util.Scanner;

/**
 * This class read user/player input from the keyboard the input is divided into set of fields, which is return as array
 * of strings
 *
 * @author Hanan Fadel July 2020
 */
public class Input {

    public static int getInt(String prompt, int start, int end) {
        while (true) {
            System.out.println(prompt);
            try {
                Scanner scan = new Scanner(System.in);
                int input = scan.nextInt();
                if (input >= start && input <= end) {
                    return input;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                if (start > end) {
                    System.out.println("The argument 'start' should less than 'end', please re-enter:");
                } else {
                    System.out.println("Please enter the number between " + start + " and " + end);

                }
            }
        }
    }

    public static String getString(String prompt, int start, int end) {
        while (true) {
            System.out.println(prompt);
            try {
                Scanner scan = new Scanner(System.in);
                String input = scan.next();
                if (input.length() >= start && input.length() <= end) {
                    return input;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                if (start == end) {
                    System.out.println("Please enter a string of length " + start);
                } else if (start > end) {
                    System.out.println("The argument 'start' should less than 'end', please re-enter:");
                } else {
                    System.out.println("Please enter the string between " + start + " and " + end);
                }
            }
        }
    }

    public static String getString(String prompt, int length) {
        return getString(prompt, length, length);
    }

    public static String getString(String prompt, String[] strArray) {
        String strReturn = getString(prompt, 1, 100);
        String strAllChoice = "";
        for (int i = 0; i < strArray.length; i++) {
            strAllChoice = strArray[i] + "/";
        }
        strAllChoice += "\b\n";
        while (true) {
            for (int i = 0; i < strArray.length; i++) {
                if (strArray[i].equals(strReturn)) {
                    return strReturn;
                }
            }
            System.out.println("Input error, please re-enter all choices below: ");
            System.out.println("Choices: "+strAllChoice);
        }
    }

}
