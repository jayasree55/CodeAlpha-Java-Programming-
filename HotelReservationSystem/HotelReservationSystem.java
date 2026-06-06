import java.io.*;
import java.util.*;

class Room {
    private int roomNo;
    private String category;
    private boolean booked;

    public Room(int roomNo, String category) {
        this.roomNo = roomNo;
        this.category = category;
        this.booked = false;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public String getCategory() {
        return category;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}

class Reservation {
    private String customerName;
    private int roomNo;
    private String category;
    private double amount;

    public Reservation(String customerName, int roomNo,
            String category, double amount) {
        this.customerName = customerName;
        this.roomNo = roomNo;
        this.category = category;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return customerName + "," + roomNo + "," + category + "," + amount;
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<Room>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "bookings.txt";

    public static void main(String[] args) {

        initializeRooms();

        while (true) {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    System.out.println("Thank You!");
                    return;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));
    }

    static void searchRooms() {
        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println("Room No: " + room.getRoomNo()
                        + " | Category: " + room.getCategory());
            }
        }
    }

    static void bookRoom() {

        sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Room Category (Standard/Deluxe/Suite): ");
        String category = sc.nextLine();

        for (Room room : rooms) {

            if (!room.isBooked() &&
                    room.getCategory().equalsIgnoreCase(category)) {

                double amount = getPrice(category);

                System.out.println("Amount to Pay: Rs." + amount);
                System.out.print("Confirm Payment (yes/no): ");
                String payment = sc.nextLine();

                if (payment.equalsIgnoreCase("yes")) {

                    room.setBooked(true);

                    Reservation reservation = new Reservation(name,
                            room.getRoomNo(),
                            category,
                            amount);

                    saveBooking(reservation);

                    System.out.println("Booking Successful!");
                    System.out.println("Allocated Room: "
                            + room.getRoomNo());

                } else {
                    System.out.println("Payment Cancelled!");
                }
                return;
            }
        }

        System.out.println("No Rooms Available!");
    }

    static double getPrice(String category) {

        if (category.equalsIgnoreCase("Standard"))
            return 2000;
        else if (category.equalsIgnoreCase("Deluxe"))
            return 3500;
        else if (category.equalsIgnoreCase("Suite"))
            return 5000;

        return 0;
    }

    static void saveBooking(Reservation reservation) {

        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            fw.write(reservation.toString());
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error Saving Booking!");
        }
    }

    static void cancelReservation() {

        System.out.print("Enter Room Number to Cancel: ");
        int roomNumber = sc.nextInt();

        for (Room room : rooms) {
            if (room.getRoomNo() == roomNumber && room.isBooked()) {
                room.setBooked(false);
                System.out.println("Reservation Cancelled!");
                return;
            }
        }

        System.out.println("Booking Not Found!");
    }

    static void viewBookings() {

        try {

            File file = new File(FILE_NAME);

            if (!file.exists()) {
                System.out.println("No Bookings Available.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));

            String line;

            System.out.println("\n----- Booking Details -----");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                System.out.println("Customer Name : " + data[0]);
                System.out.println("Room Number   : " + data[1]);
                System.out.println("Category      : " + data[2]);
                System.out.println("Amount Paid   : Rs." + data[3]);
                System.out.println("---------------------------");
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error Reading File!");
        }
    }
}