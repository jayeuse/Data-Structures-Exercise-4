package IveeFolder;

import java.util.Scanner;
import java.util.Stacks;

public class Operations {
    Scanner sc = new Scanner(System.in);

    public void Infix_to_Postfix(){
        boolean ContinueConversion = true;

        while(ContinueConversion){
            try{
            System.out.println("Infix to Postfix Operation");
            System.out.println("Enter an Infix Expression: ");
                String InfixEx = scanner.nextLine();

                String PostfixEx = InfixtoPostfix(InfixEx);
            System.out.println("From Infix to PostFIX Expression: " + PostfixEx);

    }   catch (Exception e){
        System.out.println("Error! Please enter a valid INFIX EXPRESSION.");
        System.out.print("Do you want to convert another expression? (Y/N): ");
            String UserInput = scanner.nextLine().trim().toLowerCase();

        if (!UserInput.equals("yes")){
                ContinueConversion = false;
                    System.out.println("You are now exiting!");
                
            }
        }
        scanner.close();
    }  
}  private String InfixtoPostfix(String InfixEx) {
    String PostfixEx = "";
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < infix.length(); i++) {
        char ch = infix.charAt(i);
        if (ch == '(') {
            stack.push(ch);
        } else if (ch == ')') {
            while (!stack.isEmpty() && stack.peek() != '(') {
                postfix += stack.pop();
                    throw new Exception("Invalid expression");
            }
            stack.pop();
        } else if (Character.isLetterOrDigit(ch)) {
            postfix += ch;
        } else {
            while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                postfix += stack.pop();
            }
            stack.push(ch);
        throw new Exception("Invalid character in expression: " + stack.peek);
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

