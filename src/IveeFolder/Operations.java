package IveeFolder;

import java.util.Scanner;
import java.util.Stack;

public class Operations {
    Scanner sc = new Scanner(System.in);

    public void Infix_to_Postfix(){
        boolean ContinueConversion = true;

        while(ContinueConversion){
            try{
            System.out.println("Infix to Postfix Operation");
            System.out.println("Enter an Infix Expression: ");
                String InfixEx = sc.nextLine();

                String PostfixEx = InfixtoPostfix(InfixEx);
            System.out.println("From Infix to PostFIX Expression: " + PostfixEx);

    }   catch (Exception e){
        System.out.println("Error! Please enter a valid INFIX EXPRESSION.");
        System.out.print("Do you want to convert another expression? (Y/N): ");
            String UserInput = sc.nextLine().trim().toLowerCase();

        if (!UserInput.equals("yes")){
                ContinueConversion = false;
                    System.out.println("You are now exiting!");
                
            }
        }
        sc.close();
    }  
}  private String InfixtoPostfix(String InfixEx) throws Exception {
    String PostfixEx = "";
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < InfixEx.length(); i++) {
        char ch = InfixEx.charAt(i);
        if (ch == '(') {
            stack.push(ch);
        } else if (ch == ')') {
            while (!stack.isEmpty() && stack.peek() != '(') {
                PostfixEx += stack.pop();
                    throw new Exception("Invalid expression");
            }
            stack.pop();
        } else if (Character.isLetterOrDigit(ch)) {
            PostfixEx += ch;
        } else {
            while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                PostfixEx += stack.pop();
            }
            stack.push(ch);
        throw new Exception("Invalid character in expression: " + stack.peek());
        }       
        
    }

    while (!stack.isEmpty()) {
        PostfixEx += stack.pop();
    }

    return PostfixEx;
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

