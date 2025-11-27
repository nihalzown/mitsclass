import java.util.Scanner;

class OddNumberException extends Exception {
    public OddNumberException(String message) {
        super(message);
    }
}

public class exp5_4{
    public static void checkOddNumber(int number) throws OddNumberException {
        if (number % 2 != 0) {
            throw new OddNumberException("Exception: The number " + number + " is odd.");
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
        } catch (OddNumberException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
}