import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu extends Operations{
    Scanner sc = new Scanner(System.in);
    Operations operations = new Operations();

    String filler;

    public void createMenu () {

        while (true){
            System.out.println("==================================");
            System.out.println("|        Stacks Operations       |");
            System.out.println("==================================");
            System.out.println("|                                |");
            System.out.println("|  1. Infix to Postfix           |");
            System.out.println("|  2. Infix to Prefix            |");
            System.out.println("|  3. Postfix to Infix           |");
            System.out.println("|  0. Stop                       |");
            System.out.println("|                                |");
            System.out.println("==================================");
            System.out.print("Enter Choice: ");

            try{

                int choice = sc.nextInt();
                sc.nextLine();
                boolean isRunning = true;

                switch (choice){
                    case 1:
                        do {
                            operations.Infix_to_Postfix();
                            String tryAgain;
                            while (true) {
                                System.out.print("Try Again? (Y/N): ");
                                tryAgain = sc.nextLine().trim();

                                if (tryAgain.equalsIgnoreCase("y") || tryAgain.equalsIgnoreCase("n")) {
                                    clearScreen();
                                    break;
                                } else {
                                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                                }
                            }
                            if (tryAgain.equalsIgnoreCase("n")) {
                                isRunning = false;
                            }
                        } while (isRunning);
                        break;
                    case 2:
                        do {
                            operations.Infix_to_Prefix();
                            String tryAgain;
                            while (true) {
                                System.out.print("Try Again? (Y/N): ");
                                tryAgain = sc.nextLine().trim();

                                if (tryAgain.equalsIgnoreCase("y") || tryAgain.equalsIgnoreCase("n")) {
                                    clearScreen();
                                    break;
                                } else {
                                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                                }
                            }
                            if (tryAgain.equalsIgnoreCase("n")) {
                                isRunning = false;
                            }
                        } while (isRunning);
                        break;
                    case 3:
                        do {
                            operations.Postfix_to_Infix();
                            String tryAgain;
                            while (true) {
                                System.out.print("Try Again? (Y/N): ");
                                tryAgain = sc.nextLine().trim();

                                if (tryAgain.equalsIgnoreCase("y") || tryAgain.equalsIgnoreCase("n")) {
                                    clearScreen();
                                    break;
                                } else {
                                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                                }
                            }
                            if (tryAgain.equalsIgnoreCase("n")) {
                                isRunning = false;
                            }
                        } while (isRunning);
                        break;
                    case 0:
                        System.out.println("Ending Program");
                        return;
                    default:
                        System.out.println("Error: Please Enter a valid choice!");
                        break;
                }

            }catch (InputMismatchException e) {
                System.out.println("Error: Please Enter a valid choice!");
                sc.nextLine();
            }
        }
    }

    public void clearScreen(){
        System.out.println("Enter to Continue");
        filler = sc.nextLine();

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
}
