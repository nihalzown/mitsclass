import java.util.Scanner;
abstract class Compartment {
    protected int availableSeats;

    public Compartment(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public abstract void notice();

    public void bookTicket(int seats) {
        if (seats <= availableSeats) {
            availableSeats -= seats;
            System.out.println(seats + " seats booked successfully.");
        } else {
            System.out.println("Not enough seats available.");
        }
    }
}

class FirstClass extends Compartment {

    public FirstClass(int availableSeats) {
        super(availableSeats);
    }

    public void notice() {
        System.out.println("First Class Compartment: " + availableSeats + " seats available.");
    }
}

class GeneralClass extends Compartment {

    public GeneralClass(int availableSeats) {
        super(availableSeats);
    }

    public void notice() {
        System.out.println("General Class Compartment: " + availableSeats + " seats available.");
    }
}

public class exp4_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        FirstClass firstClass = new FirstClass(50);
        GeneralClass generalClass = new GeneralClass(100);

        firstClass.notice();
        generalClass.notice();

        while(true){
            System.out.println("Choose an option: 1. Book First Class Ticket 2. Book General Class Ticket 3. show available seats 4. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter number of seats to book:");
                    int firstClassSeats = sc.nextInt();
                    firstClass.bookTicket(firstClassSeats);
                    break;
                case 2:
                    System.out.println("Enter number of seats to book:");
                    int generalClassSeats = sc.nextInt();
                    generalClass.bookTicket(generalClassSeats);
                    break;
                case 3:
                    firstClass.notice();
                    generalClass.notice();
                    break;
                case 4:
                    System.exit(0);
                    sc.close();
                default:
                    System.out.println("Invalid choice.");
            }
        } 
    }
}