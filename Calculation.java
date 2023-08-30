package Java.Calculator;

import java.util.Stack;

public class Calculation {
    private String operation;
    private double result;

    public Calculation(String operation) {
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void calculate() throws InvalidOperationException, NumberFormatException {
        this.result = evaluateExpression(operation);
        /*
         * Here i need to code a method, that uses the operation-String to extract a calculation and
         * saves in 'result' the result of that calculation. Otherwise it throws an Exception if
         * it isn't a valid calculation.
         */
        

    }

    private double evaluateExpression(String expression) throws InvalidOperationException, NumberFormatException {
        Stack<Double> numbers = new Stack<Double>();
        Stack<Character> operators = new Stack<Character>();

        for(int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if(Character.isDigit(c)) {
                StringBuilder numStr = new StringBuilder();
                while(i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    numStr.append(expression.charAt(i++));
                }
                i--;
                double num = Double.parseDouble(numStr.toString());
                numbers.push(num);
            } else if(c == '+' || c == '-' || c == '*' || c == '/') {
                while(!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    performCalculation(numbers, operators);
                }
                operators.push(c);
            } else {
                throw new InvalidOperationException("Calculation not valid.");
            }
        }
        while(!operators.isEmpty()) {
            performCalculation(numbers, operators);
        }
        return numbers.pop();
    }
            

    private int precedence(char operator) {
        switch(operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
        
    private void performCalculation(Stack<Double> numbers, Stack<Character> operators) {
        double num2 = numbers.pop();
        double num1 = numbers.pop();
        char operator = operators.pop();

        switch(operator) {
            case '+':
                numbers.push(num1 + num2);
                break;
            case '-':
                numbers.push(num1 - num2);
                break;
            case '*':
                numbers.push(num1 * num2);
                break;
            case '/':
                numbers.push(num1 / num2);
                break;
        }
    }
}