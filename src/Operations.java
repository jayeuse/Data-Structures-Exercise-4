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
                System.out.println("Converted expression from Infix to POSTFIX: " + postfix);
                System.out.println("-------------------------* * *-------------------------");
                continueConversion = false; //ADDED THIS TO END THE LOOP WHENEVER THERES A SUCCESSFUL CONVERSION
            }catch (Exception e){
                System.out.println("Error: Please enter a valid INFIX EXPRESSION!");
                sc.nextLine();
            }

            /* REMOVED THIS CODE, MOVED THE TRY AGAIN IN THE MAIN MENU
            System.out.print("Do you want to convert another expression? (Y/N): ");
            String UserInput = sc.nextLine().trim().toUpperCase().toLowerCase();

            if (!UserInput.equals("Y")) {
                continueConversion = false;
                System.out.println("Thank you for using the program!");
            }*/
        }
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
