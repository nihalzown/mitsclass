import java.util.InputMismatchException;
import java.util.Scanner;

public class exp5_3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();

                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();

                System.out.print("Enter an operator (+, -, *, /) or 'q' to quit: ");
                char operator = scanner.next().charAt(0);

                if (operator == 'q') {
                    break;
                }

                double result;
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            throw new ArithmeticException("Cannot divide by zero");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator");
                }

                System.out.println("The result is: " + result);
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter numeric values.");
                scanner.next(); // Clear the invalid input
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}