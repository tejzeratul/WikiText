package com.tejzeratul;

import java.io.IOException;
import java.util.Scanner;

/*
 * Launcher.java
 *
 * The class contain main method which calls other methods as needed
 *
 * @author Tejas Padliya
 *
 */

public class Launcher {

    /**
     * main()
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        /**
         * title name to be used in scraping operation
         */
        String inputTitle = "";

        /**
         * Fetch object to perform web scrap operations
         */
        Fetch objFetch;

        /**
         * String to hold result text
         */
        String resultParagraph;

        /**
         * Scanner object to read input from prompt
         */
        Scanner objScanner;

        // Check for command line arguments
        if (args.length > 0) {
            inputTitle = args[0];

        } else {

            // Read user input from prompt
            objScanner = new Scanner(System.in);
            while (inputTitle.trim().isEmpty()) {
                System.out.println("Enter title to get first paragraph from Wikipedia");

                inputTitle = objScanner.nextLine();
            }

            try {
                objScanner.close();
                System.in.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        objFetch = new Fetch();
        resultParagraph = objFetch.getFirstWikiPara(inputTitle);

        System.out.println(resultParagraph);
    }
}
