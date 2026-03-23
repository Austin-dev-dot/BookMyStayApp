/**
 * BookMyStayApp acts as the main entry point for the Hotel Booking Management System.
 * This class establishes a clear starting point for the application.
 * 
 * @author Austin Paul
 * @version 1.0
 */
public class BookMyStayApp {

    /**
     * The main method starts the application.
     * 
     * @param args Command line arguments (not used currently).
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Welcome to the Hotel Booking Management System!");
        System.out.println("Application Name: BookMyStayApp");
        System.out.println("Version: v2.0");
        System.out.println("==================================================");

        // UC2: Create room instances
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // UC2: Store availability using individual variables
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        System.out.println("\n--- Initializing Rooms and Static Availability ---");
        System.out.println(singleRoom.toString() + " | Available: " + singleRoomAvailability);
        System.out.println(doubleRoom.toString() + " | Available: " + doubleRoomAvailability);
        System.out.println(suiteRoom.toString() + " | Available: " + suiteRoomAvailability);
        System.out.println("==================================================");
    }
}
