package bsu.edu.cs;

import java.io.IOException;
import java.util.Scanner;

public class main{
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean running = true;
        while(running) {
            System.out.println("Enter \"q\" to quit the program at any time.");
            System.out.print("\nPlease enter an article title you would like to search for: ");
            String response = scanner.nextLine();

            if (response.equals("q")) {
                running = false;
            } else {
                MediaWikiReader.runReader(response);
            }
        }
    }
}
