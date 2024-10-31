package RudyFolder;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    Scanner sc = new Scanner(System.in);
    Operations operations = new Operations();

    public void createMenu () {

        while (true){
            System.out.println("Stacks Operations");
            System.out.println("Conversions");
            System.out.println("1. Infix to Postfix");
            System.out.println("2. Infix to Prefix");
            System.out.println("3. Postfix to Prefix");
            System.out.println("0. Stop");
            System.out.print("Enter Choice: ");

            try{

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice){
                    case 1:
                        operations.Infix_to_Postfix();
                        break;
                    case 2:
                        operations.Infix_to_Prefix();
                        break;
                    case 3:
                        operations.Postfix_to_Prefix();
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
}
