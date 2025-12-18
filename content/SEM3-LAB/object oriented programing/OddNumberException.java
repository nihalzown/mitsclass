// Aim: Program to perform Exception Handling operations (Odd Number Exception).
import java.util.Scanner;

class OddNumberExceptionTask extends Exception {
    public OddNumberExceptionTask(String message) {
        super(message);
    }
}

public class OddNumberException{
    public static void checkOddNumber(int number) throws OddNumberExceptionTask {
        if (number % 2 != 0) {
            throw new OddNumberExceptionTask("Exception: The number " + number + " is odd.");
        } else {
            System.out.println("The number " + number + " is even.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to check: ");
        int number = scanner.nextInt();

        try {
            checkOddNumber(number);
        } catch (OddNumberExceptionTask e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
}