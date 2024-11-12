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

                if (infix.isEmpty()) {
                    throw new IllegalArgumentException();
                }

                if (!infix.matches(".*[+\\-*/()].*")) {
                    throw new IllegalArgumentException();
                }

                if (!infix.matches("[a-zA-Z+\\-*/() ]+")) {
                    throw new IllegalArgumentException();
                }
                System.out.println("-------------------------* * *-------------------------");
                System.out.println("Infix Expression: " + infix);

                String postfix = InToPos(infix);
                System.out.println("-------------------------* * *-------------------------");
                System.out.println("Converted expression from Infix to POSTFIX: " + postfix);
                System.out.println("-------------------------* * *-------------------------");
                continueConversion = false; //ADDED THIS TO END THE LOOP WHENEVER THERES A SUCCESSFUL CONVERSION
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid INFIX EXPRESSION.");
            }

        }
    }

    private String InToPos(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char expressionCharacter = infix.charAt(i);

            if (Character.isLetterOrDigit(expressionCharacter)) {
                postfix.append(expressionCharacter);
            } else if (expressionCharacter == '(') {

                stack.push(expressionCharacter);
            } else if (expressionCharacter == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException();
                }
                stack.pop();

            } else if (Operator(expressionCharacter)) {
                while (!stack.isEmpty() && orderOfOperations(expressionCharacter) <= orderOfOperations(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(expressionCharacter);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                throw new IllegalArgumentException();
            }
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    private static int orderOfOperations(char characterExpression) {
        switch (characterExpression) {
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

    private static boolean Operator(char characterExpression) {
        return characterExpression == '+' || characterExpression == '-' || characterExpression == '*' || characterExpression == '/' || characterExpression == '^';
    }

    public void Infix_to_Prefix() {
        while (true) {
            try{
                System.out.println("-------------------------* * *-------------------------");
                System.out.println("Infix to Prefix Operation");
                System.out.println("-------------------------* * *-------------------------");
                System.out.print("Enter infix expression: ");
                String infix = sc.nextLine().trim();

                infix = infix.toUpperCase();

                if (infix.isEmpty()) {
                    throw new IllegalArgumentException();
                }

                if (!infix.matches(".*[+\\-*/()].*")) {
                    throw new IllegalArgumentException();
                }

                if (!infix.matches("[a-zA-Z+\\-*/() ]+")) {
                    throw new IllegalArgumentException();
                }

                System.out.println("-------------------------* * *-------------------------");
                System.out.println("Infix Expression: " + infix);

                String reversedInfix = reverseString(infix);
                System.out.println("Reversed Infix Expression: " + reversedInfix); //Remove this line if you don't want to see the reversed infix expression
                String postfix = InToPos(reversedInfix); //Uses Ivee's Method
                System.out.println("Reverse Postfix Expression: " + postfix);
                String prefix = reverseString(postfix); //Remove this line if you don't want to see the reversed postfix expression
                System.out.println("-------------------------* * *-------------------------");
                System.out.println("Converted expression from Infix to PREFIX: " + prefix);
                System.out.println("-------------------------* * *-------------------------");

                break;

            }
            catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid INFIX EXPRESSION.");
            }
        }
    }

    private String reverseString(String str) {
        StringBuilder reversed = new StringBuilder(str.length());
        for (int i = str.length() - 1; i >= 0; i--) {
            char expressionCharacter = str.charAt(i);
            if (expressionCharacter == '(') {
                reversed.append(')');
            } else if (expressionCharacter == ')') {
                reversed.append('(');
            } else {
                reversed.append(expressionCharacter);
            }
        }
        return reversed.toString();
    }

    public void Postfix_to_Infix() {
        System.out.println("-------------------------* * *-------------------------");
        System.out.println("Postfix to Infix Operation");
        System.out.println("-------------------------* * *-------------------------");
        System.out.print("Enter a Postfix Expression: ");
        String postfix = sc.nextLine().toUpperCase().trim();

        if (!isValidPostfix(postfix)) {
            System.out.println("Error: Invalid POSTFIX EXPRESSION!");
            return;
        }

        System.out.println("-------------------------* * *-------------------------");
        System.out.println("Postfix Expression: " + postfix);

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char characterExpression = postfix.charAt(i);

            if (Character.isWhitespace(characterExpression)) {
                continue;
            }

            if (Character.isLetterOrDigit(characterExpression)) {
                stack.push(String.valueOf(characterExpression));
            }
            else if (Operator(characterExpression)) {
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
        System.out.println("-------------------------* * *-------------------------");
        System.out.println("Converted expression from postfix to INFIX: " + infixExpression);
        System.out.println("-------------------------* * *-------------------------");
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
            } else if (Operator(expressionCharacter)) {
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

    private String applyPrecedence(String operand1, char operator, String operand2) {
        boolean leftNeedsParentheses = needsParentheses(operand1, operator, true);
        boolean rightNeedsParentheses = needsParentheses(operand2, operator, false);

        String expr1 = leftNeedsParentheses ? "(" + operand1 + ")" : operand1;
        String expr2 = rightNeedsParentheses ? "(" + operand2 + ")" : operand2;

        return expr1 + operator + expr2;
    }

    private boolean needsParentheses(String operand, char operator, boolean isLeftOperand) {
        char lastOperator = getLastOperator(operand);
        boolean result;

        if (lastOperator == '\0') {
            return false;
        }
        int currentPrecedence = orderOfOperations(operator);
        int lastPrecedence = orderOfOperations(lastOperator);

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
            if (Operator(characterExpression)) {
                return characterExpression;
            }
        }
        return '\0';
    }
}
