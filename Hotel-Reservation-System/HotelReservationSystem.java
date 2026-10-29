import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {
    static class Reservation {
        String guest, email, room;
        LocalDate checkIn, checkOut;
        int guests;
        double rate;
        long nights() { return ChronoUnit.DAYS.between(checkIn, checkOut); }
        double total() { return nights() * rate; }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Reservation> reservations = new ArrayList<>();
        while (true) {
            System.out.println("\n=== Hotel Reservation Desk ===\n1. Make a reservation\n2. View reservations\n3. Cancel a reservation\n0. Exit");
            int choice = readInt(in, "Choose: ", 0, 3);
            if (choice == 0) break;
            if (choice == 1) {
                Reservation r = new Reservation();
                System.out.print("Guest name: "); r.guest = in.nextLine().trim();
                while (r.guest.isEmpty()) { System.out.print("Name cannot be empty. Guest name: "); r.guest = in.nextLine().trim(); }
                System.out.print("Email: "); r.email = in.nextLine().trim();
                while (!r.email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) { System.out.print("Enter a valid email: "); r.email = in.nextLine().trim(); }
                r.checkIn = readDate(in, "Check-in date (YYYY-MM-DD): ");
                r.checkOut = readDate(in, "Check-out date (YYYY-MM-DD): ");
                while (!r.checkOut.isAfter(r.checkIn)) { System.out.println("Check-out must be after check-in."); r.checkOut = readDate(in, "Check-out date (YYYY-MM-DD): "); }
                System.out.println("Room types: 1. Standard ($90/night)  2. Deluxe ($140/night)  3. Suite ($220/night)");
                int roomType = readInt(in, "Choose room: ", 1, 3);
                r.room = new String[]{"Standard", "Deluxe", "Suite"}[roomType - 1];
                r.rate = new double[]{90, 140, 220}[roomType - 1];
                r.guests = readInt(in, "Number of guests (1-6): ", 1, 6);
                reservations.add(r);
                System.out.printf("Reservation #%d saved. %d night(s), estimated total $%.2f.%n", reservations.size(), r.nights(), r.total());
                System.out.println("This demo stores reservations only while it is running; it does not contact a real hotel.");
            } else if (choice == 2) {
                if (reservations.isEmpty()) System.out.println("No reservations yet.");
                for (int i = 0; i < reservations.size(); i++) printReservation(i + 1, reservations.get(i));
            } else {
                if (reservations.isEmpty()) { System.out.println("There are no reservations to cancel."); continue; }
                for (int i = 0; i < reservations.size(); i++) System.out.printf("%d. %s (%s)%n", i + 1, reservations.get(i).guest, reservations.get(i).checkIn);
                int index = readInt(in, "Reservation number to cancel: ", 1, reservations.size()) - 1;
                System.out.println("Cancelled reservation for " + reservations.remove(index).guest + ".");
            }
        }
        System.out.println("Thank you for using the reservation desk.");
    }
    private static void printReservation(int number, Reservation r) {
        System.out.printf("\n#%d | %s | %s%n  %s to %s (%d nights) | %d guest(s)%n  %s room | estimated total $%.2f%n", number, r.guest, r.email, r.checkIn, r.checkOut, r.nights(), r.guests, r.room, r.total());
    }
    private static LocalDate readDate(Scanner in, String prompt) { while (true) { System.out.print(prompt); try { return LocalDate.parse(in.nextLine().trim()); } catch (Exception e) { System.out.println("Please use a real date in YYYY-MM-DD format."); } } }
    private static int readInt(Scanner in, String prompt, int min, int max) { while (true) { System.out.print(prompt); try { int n=Integer.parseInt(in.nextLine().trim()); if(n>=min&&n<=max)return n; } catch(NumberFormatException ignored) {} System.out.printf("Enter a whole number from %d to %d.%n",min,max); } }
}
