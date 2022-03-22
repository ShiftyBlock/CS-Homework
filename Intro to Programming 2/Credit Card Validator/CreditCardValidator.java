/*
Awesome Group Members:
Jacob Hanna
Riona Yoshida
David Yang
 */
// Special Thanks to Bessie the Cow

import java.util.*;
import java.io.*;
import java.time.*;

public class CreditCardValidator {

    private static YearMonth currentDate = YearMonth.now();
    private static int CARD_NUMBER_LENGTH = 16;
    //Global Scanner
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // reads in the cards
        String card = readCreditCardNumber();
        int year = readYear();
        int month = readMonth(year);
        printCard(card, month, year);
    }

    // Reads in a Valid Year
    public static int readYear() {
        System.out.print("Enter the expiration Year: ");
        int year = input.nextInt();

        while (year < currentDate.getYear()) {
            System.out.println("---> That is an invalid year, Try again.");
            System.out.print("Enter the expiration Year: ");
            year = input.nextInt();
        }
        return year;
    }

    //Reads in a Valid Month
    public static int readMonth(int year) {

        System.out.print("Enter the expiration Month: ");
        int month = input.nextInt();

        while (month < 1 || month > 12) {
            System.out.println("---> That is an invalid month, Try again.");
            System.out.print("Enter the expiration Month: ");
            month = input.nextInt();

        }
        if (year >= currentDate.getYear() && month > currentDate.getMonthValue()) {
            return month;
        } else if (month == currentDate.getMonthValue()) {
            System.out.println("---> Warning: Your card expires this month! <---");
        } else {
            System.out.print("Your card has expired!");
        }
        return month;
    }

    // Reads in a Valid Credit Card
    public static String readCreditCardNumber() {
        boolean keepReadingNumbers = true;
        String card = "";
        while (keepReadingNumbers) {
            System.out.print("Enter your 16 digit credit card number: ");
            card = input.next();
            boolean first = (card.substring(0, 1).equals("4") || card.substring(0, 1).equals("5") || card.substring(0, 1).equals("6"));
            // Calls passesCheckSum method
            if (card.length() == CARD_NUMBER_LENGTH && passesCheckSum(card) && first) {
                keepReadingNumbers = false;
            } else {
                if (card.length() != CARD_NUMBER_LENGTH) {
                    System.out.println("---> The length is invalid. Try again.");
                } else if (card.length() == CARD_NUMBER_LENGTH && !first) {
                    System.out.println("---> The first digit is invalid. Try again.");
                } else if (card.length() == CARD_NUMBER_LENGTH && !passesCheckSum(card)) {
                    System.out.println("---> The number is invalid - it does not pass the check sum. Try again.");
                } else {
                    System.out.println("---> That number is incorrect. Try again.");
                }
            }

        }
        return card;
    }

    // Uses check Sum algorithm, no Arrays
    public static boolean passesCheckSum(String cardNumber) {
        int total = 0;
        for (int x = 0; x < cardNumber.length(); x++) {
            if (x % 2 == 1) {
                total += Integer.parseInt(cardNumber.substring(x, x + 1));

            }
            if (x % 2 == 0) {
                int num = Integer.parseInt(cardNumber.substring(x, x + 1)) * 2;
                if (num >= 10) num = num % 10 + 1;
                total += num;
            }

        }

        if (total % 10 == 0) return true;

        return false;
    }

    // Prints out the card in correct Format
    public static void printCard(String card, int month, int year) {
        String part1 = card.substring(0, 4);
        String part2 = card.substring(4, 8);
        String part3 = card.substring(8, 12);
        String part4 = card.substring(12, 16);
        System.out.printf("--> Your card number is (%s %s %s %s).\n", part1, part2, part3, part4);
        System.out.printf("--> exp. " + "%d/%d", month, year);
    }
}