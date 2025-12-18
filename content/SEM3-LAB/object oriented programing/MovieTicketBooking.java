
// Aim: Program to perform Abstract Class operations (Movie Ticket Booking).
import java.util.*;

abstract class MovieTicketBookingTask {
    protected int totalTickets;
    protected int soldTickets;

    public MovieTicketBookingTask(int totalTickets) {
        this.totalTickets = totalTickets;
        this.soldTickets = 0;
    }

    public void booking(int numberOfTickets) {
        if (numberOfTickets <= (totalTickets - soldTickets)) {
            soldTickets += numberOfTickets;
            System.out.println(numberOfTickets + " MovieTicketBookingTask booked successfully.");
        } else {
            System.out.println("Not enough MovieTicketBookingTask available.");
        }
    }

    public void cancellation(int numberOfTickets) {
        if (numberOfTickets <= soldTickets) {
            soldTickets -= numberOfTickets;
            System.out.println(numberOfTickets + " MovieTicketBookingTask cancelled successfully.");
        } else {
            System.out.println("Not enough MovieTicketBookingTask to cancel.");
        }
    }

    public abstract void total_collection();
}

public class MovieTicketBooking extends MovieTicketBookingTask {

    private String filmName;

    public MovieTicketBooking(String filmName, int totalTickets) {
        super(totalTickets);
        this.filmName = filmName;
    }

    public void total_collection() {
        int totalCollection = soldTickets * 150;
        System.out.println("Total collection for the film " + filmName + " is: Rs. " + totalCollection);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the film name:");
        String filmName = sc.nextLine();

        System.out.println("Enter the total number of MovieTicketBookingTask available:");
        int totalTickets = sc.nextInt();

        MovieTicketBooking movie = new MovieTicketBooking(filmName, totalTickets);

        while (true) {
            System.out.println("Choose an option: 1. Book Tickets 2. Cancel Tickets 3. Show Total Collection 4. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the number of MovieTicketBookingTask to book:");
                    int bookTickets = sc.nextInt();
                    movie.booking(bookTickets);
                    break;
                case 2:
                    System.out.println("Enter the number of MovieTicketBookingTask to cancel:");
                    int cancelTickets = sc.nextInt();
                    movie.cancellation(cancelTickets);
                    break;
                case 3:
                    movie.total_collection();
                    break;
                case 4:
                    System.exit(0);
                    sc.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}