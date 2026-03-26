import java.util.Scanner;
import java.util.Stack;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Reservation class
class Reservation {
    int reservationNumber;
    String name;
    LocalDate date;
    int persons;

    public Reservation(int reservationNumber, String name, LocalDate date, int persons) {
        this.reservationNumber = reservationNumber;
        this.name = name;
        this.date = date;
        this.persons = persons;
    }

    public void display() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Reservation No: " + reservationNumber);
        System.out.println("Name: " + name);
        System.out.println("Date: " + date.format(format));
        System.out.println("Persons: " + persons);
        System.out.println("--------------------------");
    }
}

// Main system
public class ReservationSystem {
    static Stack<Reservation> stack = new Stack<>();
    static int counter = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Reservation System ===");
            System.out.println("1. Add Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Display Reservations");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addReservation(sc);
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    displayReservations();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }

    // Add
    public static void addReservation(Scanner sc) {
        try {
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter date (yyyy-MM-dd): ");
            String inputDate = sc.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(inputDate, formatter);

            System.out.print("Enter number of persons: ");
            int persons = sc.nextInt();
            sc.nextLine();

            Reservation r = new Reservation(counter++, name, date, persons);
            stack.push(r);

            System.out.println("Reservation added!");
        } catch (Exception e) {
            System.out.println("Invalid input! Please follow format yyyy-MM-dd");
        }
    }

    // Display
    public static void displayReservations() {
        if (stack.isEmpty()) {
            System.out.println("No reservations.");
            return;
        }

        System.out.println("\n--- Reservations ---");
        for (int i = stack.size() - 1; i >= 0; i--) {
            stack.get(i).display();
        }
    }

    // Cancel (LIFO)
    public static void cancelReservation() {
        if (stack.isEmpty()) {
            System.out.println("No reservations to cancel.");
            return;
        }

        Reservation removed = stack.pop();
        System.out.println("Cancelled Reservation No: " + removed.reservationNumber);
    }
}