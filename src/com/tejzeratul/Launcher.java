package com.tejzeratul;

import java.io.IOException;
import java.util.Scanner;

/*
 * Launcher.java
 *
 * The class contain main method which calls other methods
 *
 * @author Tejas Padliya
 *
 */

public class Launcher {

    public static void main(String[] args) {

        String inputTitle="";
        Fetch objFetch;
        String resultParagraph;
        Scanner objScanner=null;

	    if(args.length>0) {
            inputTitle=args[0];

        } else {
            while(inputTitle.trim().isEmpty()) {
                System.out.println("Enter title to get first paragraph from Wikipedia");
               objScanner = new Scanner(System.in);
                inputTitle = objScanner.nextLine();
            }

            try {
                objScanner.close();
                System.in.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }

        }

        objFetch=new Fetch();
        resultParagraph=objFetch.getFirstWikiPara(inputTitle);

        System.out.println(resultParagraph);
   }
}
