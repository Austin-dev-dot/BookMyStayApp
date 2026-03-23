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
        System.out.println("Version: v3.0");
        System.out.println("==================================================");

        // UC2: Create room instances
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        System.out.println("\n--- Initializing Rooms ---");
        System.out.println(singleRoom.toString());
        System.out.println(doubleRoom.toString());
        System.out.println(suiteRoom.toString());
        System.out.println("==================================================");

        // UC3: Use Centralized Room Inventory Management
        RoomInventory inventory = new RoomInventory();
        inventory.initializeInventory(singleRoom.getRoomName(), 5);
        inventory.initializeInventory(doubleRoom.getRoomName(), 3);
        inventory.initializeInventory(suiteRoom.getRoomName(), 0); // Setting one to 0 to test search filter

        inventory.displayInventory();

        // UC4: Room Search & Availability Check
        SearchService searchService = new SearchService();
        java.util.List<Room> allRooms = java.util.Arrays.asList(singleRoom, doubleRoom, suiteRoom);
        searchService.searchAvailableRooms(allRooms, inventory);

        // UC5: Booking Request (First-Come-First-Served)
        java.util.Queue<Reservation> bookingQueue = new java.util.LinkedList<>();
        
        System.out.println("--- Incoming Booking Requests ---");
        Reservation req1 = new Reservation("Alice", singleRoom);
        Reservation req2 = new Reservation("Bob", doubleRoom);
        Reservation req3 = new Reservation("Charlie", singleRoom);
        
        bookingQueue.offer(req1);
        System.out.println("Queued: " + req1);
        
        bookingQueue.offer(req2);
        System.out.println("Queued: " + req2);
        
        bookingQueue.offer(req3);
        System.out.println("Queued: " + req3);
        
        System.out.println("Total requests waiting in queue: " + bookingQueue.size());

        // UC6: Reservation Confirmation & Room Allocation
        BookingService bookingService = new BookingService(inventory);
        bookingService.processQueue(bookingQueue);
        
        System.out.println("\n--- Post-Allocation Status ---");
        inventory.displayInventory();
        bookingService.displayAllocations();

        System.out.println("==================================================");
    }
}
