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

    public void Postfix_to_Infix() {
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
            // Pop the top two elements from the stack
            String operand2 = stack.pop();
            String operand1 = stack.pop();

            String infix = applyPrecedence(operand1, characterExpression, operand2);
            stack.push(infix);
        } else {
            System.out.println("Invalid character encountered in expression.");
            return;
        }
    }

    String infixExpression = stack.pop();
    System.out.println("Infix expression: " + infixExpression);
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

    private boolean isOperator(char operatorCharacter) {
        return operatorCharacter == '+' || operatorCharacter == '-' || operatorCharacter == '*' || operatorCharacter == '/' || operatorCharacter == '^';
    }

    private String applyPrecedence(String operand1, char operator, String operand2) {
        boolean leftNeedsParentheses = needsParentheses(operand1, operator, true);
        boolean rightNeedsParentheses = needsParentheses(operand2, operator, false);
        
        String expr1 = leftNeedsParentheses ? "(" + operand1 + ")" : operand1;
        String expr2 = rightNeedsParentheses ? "(" + operand2 + ")" : operand2;

        return expr1 + operator + expr2;
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
            default:
                return -1;
        }
    }

    private boolean needsParentheses(String operand, char operator, boolean isLeftOperand) {
        char lastOperator = getLastOperator(operand);
        boolean result;

        if (lastOperator == '\0') {
            return false; 
        }
        int currentPrecedence = precedence(operator);
        int lastPrecedence = precedence(lastOperator);

        if (isLeftOperand) {
            result = currentPrecedence > lastPrecedence;
        } else {
            result = currentPrecedence >= lastPrecedence;
        }

        return result;
    }

    private char getLastOperator(String expr) {
        for (int i = expr.length() - 1; i >= 0; i--) {
            char characterExpression = expr.charAt(i);
            if (isOperator(characterExpression)) {
                return characterExpression;
            }
        }
        return '\0';
    }

}
