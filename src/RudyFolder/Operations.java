package RudyFolder;

import java.util.Scanner;
import java.util.Stack;

/*
1. Validate Infix Expression
2. Reverse Infix Expression
3. Convert Reversed Infix Expression to Postfix Expression
4. Reverse Postfix Expression to get Prefix Expression
 */

public class Operations {
    Scanner sc = new Scanner(System.in);

    // Infix to Postfix (Main Menu Option 1)
    public void Infix_to_Postfix() {
        System.out.println("Infix to Postfix");
    }

    // Postfix to Prefix (Main Menu Option 3)
    public void Postfix_to_Infix() {
        System.out.println("Postfix to Infix");
    }

    // Infix to Prefix (Main Menu Option 2)
    public void Infix_to_Prefix() {
        while (true) {
            System.out.print("Enter infix expression: ");
            String infix = sc.nextLine().trim();

            if (!isValidInfix(infix)) {
                System.out.println("Invalid infix expression. Returning to main menu.");
                return;
            } else {
                String reversedInfix = reverseString(infix);
                String postfix = infixToPostfix(reversedInfix);
                String prefix = reverseString(postfix);
                System.out.println("Prefix expression: " + prefix);

                System.out.print("Try Again? (Y/N): ");
                String tryAgain = sc.nextLine().trim().toLowerCase();
                if (!tryAgain.equals("y")) {
                    return;
                }
            }
        }
    }

    // Validate Infix Expression
    private boolean isValidInfix(String infix) {
        return infix.matches("[a-zA-Z0-9+\\-*/^() ]+");
    }

    // Reverse Validated Infix Expression
    private String reverseString(String str) {
        StringBuilder reversed = new StringBuilder(str.length());
        for (int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if (ch == '(') {
                reversed.append(')');
            } else if (ch == ')') {
                reversed.append('(');
            } else {
                reversed.append(ch);
            }
        }
        return reversed.toString();
    }

    // Convert Reversed Infix Expression to Postfix Expression
    private String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char ch : infix.toCharArray()) {
            if (Character.isWhitespace(ch)) {
                continue;
            }
            if (Character.isLetterOrDigit(ch)) {
                postfix.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    // Precedence of Operators (Order of Operations)
    private int precedence(char ch) {
        switch (ch) {
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
}