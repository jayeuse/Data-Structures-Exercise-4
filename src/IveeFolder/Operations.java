package IveeFolder;

import java.util.Scanner;
import java.util.Stack;

public class Operations {
    Scanner sc = new Scanner(System.in);

    public void Infix_to_Postfix(){
        boolean continueConversion = true;
    
    while(continueConversion){
        try{

            System.out.println("-------------------------* * *-------------------------");
            System.out.println("Infix to Postfix Operation");
            System.out.println("-------------------------* * *-------------------------");
            System.out.print("Enter Infix Expression: ");
                String infix = sc.nextLine();

                infix = infix.toUpperCase();           
                infix = infix.replaceAll("\\s+", ""); 

                if (infix.matches(".*\\d.*")) { 
                    System.out.println("Error: Numbers are not allowed in the expression!");
                    continue; 
                }
                System.out.println("-------------------------* * *-------------------------");
            System.out.println("Infix Expression: " + infix);
            
                String postfix = InToPos(infix);
                System.out.println("-------------------------* * *-------------------------");
            System.out.println("Coverted expression from Infix to POSTFIX: " + postfix);
            System.out.println("-------------------------* * *-------------------------");
            }catch (Exception e){
                System.out.println("Error: Please enter a valid INFIX EXPRESSION!");
                sc.nextLine();
            }  

                System.out.print("Do you want to convert another expression? (Y/N): ");
                String UserInput = sc.nextLine().trim().toUpperCase().toLowerCase();
    
                if (!UserInput.equals("Y")) {
                    continueConversion = false;
                    System.out.println("Thank you for using the program!");
                }
            }
    
            sc.close();
        }  
        private String InToPos(String infix) {

            String postfix = "";
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < infix.length(); i++) {
                char ch = infix.charAt(i);
                if (ch == '(') {
                    stack.push(ch);
                } else if (ch == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        postfix += stack.pop();
                    }
                    stack.pop();
                } else if (Character.isLetterOrDigit(ch)) {
                    postfix += ch;
                } else {
                    while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                        postfix += stack.pop();
                    }
                    stack.push(ch);
                }
            }
    
            while (!stack.isEmpty()) {
                postfix += stack.pop();
            }
    
            return postfix;
        }
    
        private int precedence(char operator) {
            switch (operator) {
                case '+':
                case '-':
                    return 1;
                case '*':
                case '/':
                    return 2;
                case '^':
                    return 3;
            }
        return -1;

    }


    public void Infix_to_Prefix(){
        System.out.println("Infix to Prefix Operation");
    }

    public void Postfix_to_Prefix(){
        System.out.println("Postfix to Prefix Operation");
    }
}

