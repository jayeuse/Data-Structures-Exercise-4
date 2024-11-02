package CeejayFolder;

import java.util.Scanner;
import java.util.Stack;

public class Operations {
    Scanner sc = new Scanner(System.in);

    public void Infix_to_Postfix(){
        System.out.println("Infix to Postfix Operation");
    }

    public void Infix_to_Prefix(){
        System.out.println("Infix to Prefix Operation");
    }

    public void Postfix_to_Prefix(){
        System.out.println("Postfix to Prefix Operation");

        System.out.print("Enter a Postfix Expression: ");
        String postfix = sc.nextLine();

        if (!isValidPostfix(postfix)) {
            System.out.println("Invalid Postfix Expression!");
            return;
        }

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < postfix.length(); i++) {
            char characterExpression = postfix.charAt(i);

            if (Character.isWhitespace(characterExpression)) {
                continue;
            }

            if (Character.isLetterOrDigit(characterExpression)) {
                stack.push(String.valueOf(characterExpression));
            } 

            else if (isOperator(characterExpression)) {

                String operand2 = stack.pop();
                String operand1 = stack.pop();

                String prefix = characterExpression + operand1 + operand2;

                stack.push(prefix);
            } else {
                System.out.println("Invalid character encountered in expression.");
                return;
            }
        }

        String prefixExpression = stack.pop();
        System.out.println("Prefix expression: " + prefixExpression);
    }

    private boolean isValidPostfix(String postfix) {
        int operandCount = 0;
        for (int i = 0; i < postfix.length(); i++) {
            char expressionCharacter = postfix.charAt(i);

            if (Character.isWhitespace(expressionCharacter)) {
                continue;
            }
            if (Character.isLetterOrDigit(expressionCharacter)) {
                operandCount++;
            } else if (isOperator(expressionCharacter)) {
                if (operandCount < 2) {
                    return false;
                }
                operandCount--;
            } else {
                return false;
            }
        }
        
        return operandCount == 1;
    }

    private boolean isOperator(char opertorCharacter) {
        return opertorCharacter == '+' || opertorCharacter == '-' || opertorCharacter == '*' || opertorCharacter == '/' || opertorCharacter == '^';
    }
}
