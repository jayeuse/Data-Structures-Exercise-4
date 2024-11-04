package RudyFolder;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    Scanner sc = new Scanner(System.in);
    Operations operations = new Operations();

    public void createMenu() {
        while (true) {
            clearScreen();
            System.out.println("=================================");
            System.out.println("|         Stacks Operations      |");
            System.out.println("==================================");
            System.out.println("|                                |");
            System.out.println("|  1. Infix to Postfix           |");
            System.out.println("|  2. Infix to Prefix            |");
            System.out.println("|  3. Postfix to Infix           |");
            System.out.println("|  0. Stop                       |");
            System.out.println("|                                |");
            System.out.println("==================================");
            System.out.print("Enter Choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        operations.Infix_to_Postfix();
                        break;
                    case 2:
                        operations.Infix_to_Prefix();
                        break;
                    case 3:
                        operations.Postfix_to_Infix();
                        break;
                    case 0:
                        System.out.println("Ending Program");
                        return;
                    default:
                        System.out.println("Error: Please Enter a valid choice!");
                        break;
                }
                enterToContinue();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please Enter a valid choice!");
                sc.nextLine();
                enterToContinue();
            }
        }
    }

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enterToContinue() {
        System.out.println("Enter to Continue...");
        sc.nextLine();
    }
}