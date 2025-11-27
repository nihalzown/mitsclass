import java.util.*;

abstract class tickets{
    protected int totalTickets;
    protected int soldTickets;

    public tickets(int totalTickets) {
        this.totalTickets = totalTickets;
        this.soldTickets = 0;
    }

    public void booking(int numberOfTickets) {
        if (numberOfTickets <= (totalTickets - soldTickets)) {
            soldTickets += numberOfTickets;
            System.out.println(numberOfTickets + " tickets booked successfully.");
        } else {
            System.out.println("Not enough tickets available.");
        }
    }

    public void cancellation(int numberOfTickets) {
        if (numberOfTickets <= soldTickets) {
            soldTickets -= numberOfTickets;
            System.out.println(numberOfTickets + " tickets cancelled successfully.");
        } else {
            System.out.println("Not enough tickets to cancel.");
        }
    }

    public abstract void total_collection();
}

 class exp4_1 extends tickets{

    private String filmName;

    public exp4_1(String filmName, int totalTickets) {
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

        System.out.println("Enter the total number of tickets available:");
        int totalTickets = sc.nextInt();

        exp4_1 movie = new exp4_1(filmName, totalTickets);

        while (true) {
            System.out.println("Choose an option: 1. Book Tickets 2. Cancel Tickets 3. Show Total Collection 4. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the number of tickets to book:");
                    int bookTickets = sc.nextInt();
                    movie.booking(bookTickets);
                    break;
                case 2:
                    System.out.println("Enter the number of tickets to cancel:");
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