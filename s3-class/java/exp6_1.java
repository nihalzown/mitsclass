import java.util.*;

public class exp6_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of integers: ");
        String line = scanner.nextLine();

        StringTokenizer tokenizer = new StringTokenizer(line);
        int sum = 0;

        System.out.println("The integers are:");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            try {
                int number = Integer.parseInt(token);
                System.out.println(number);
                sum += number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer: " + token);
            }
        }

        System.out.println("Sum of all integers: " + sum);
        scanner.close();
    }
}