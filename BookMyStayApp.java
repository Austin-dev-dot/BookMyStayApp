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
        
        // Add more requests to show concurrency
        Reservation req4 = new Reservation("Dave", singleRoom);
        Reservation req5 = new Reservation("Eve", singleRoom);
        Reservation req6 = new Reservation("Frank", singleRoom);
        Reservation req7 = new Reservation("Grace", doubleRoom);
        Reservation req8 = new Reservation("Heidi", doubleRoom);
        
        bookingQueue.offer(req4);
        bookingQueue.offer(req5);
        bookingQueue.offer(req6);
        bookingQueue.offer(req7);
        bookingQueue.offer(req8);
        
        System.out.println("Total requests waiting in queue: " + bookingQueue.size());

        // UC8: Booking History & Reporting
        BookingHistory bookingHistory = new BookingHistory();

        // UC6 & UC11: Concurrent Reservation Confirmation & Room Allocation
        BookingService bookingService = new BookingService(inventory, bookingHistory);
        
        System.out.println("\n--- Starting Concurrent Booking ---");
        Thread t1 = new Thread(() -> bookingService.processQueue(bookingQueue), "Thread-1");
        Thread t2 = new Thread(() -> bookingService.processQueue(bookingQueue), "Thread-2");
        Thread t3 = new Thread(() -> bookingService.processQueue(bookingQueue), "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("--- Completed Processing ---");
        
        System.out.println("\n--- Post-Allocation Status ---");
        inventory.displayInventory();
        bookingService.displayAllocations();

        // UC7: Add-On Service Selection
        AddOnServiceManager addOnManager = new AddOnServiceManager();
        AddOnService spaService = new AddOnService("Spa Access", 50.0);
        AddOnService breakfastService = new AddOnService("Breakfast Buffet", 25.0);

        System.out.println("\n--- Guest " + req1.getGuestName() + " Selecting Add-Ons ---");
        addOnManager.addService(req1.getReservationId(), spaService);
        addOnManager.addService(req1.getReservationId(), breakfastService);
        
        System.out.println("\n--- Guest " + req2.getGuestName() + " Selecting Add-Ons ---");
        addOnManager.addService(req2.getReservationId(), breakfastService);

        addOnManager.displayServices(req1.getReservationId());
        addOnManager.displayServices(req2.getReservationId());

        // UC8: Generate Report
        BookingReportService reportService = new BookingReportService(bookingHistory);
        reportService.generateSummaryReport();

        // UC10: Booking Cancellation & Inventory Rollback
        CancellationService cancellationService = new CancellationService(inventory, bookingHistory);
        cancellationService.cancelBooking(req1); // Cancel Alice's booking
        cancellationService.displayRollbackStack();

        System.out.println("\n--- Post-Cancellation Status ---");
        inventory.displayInventory();
        reportService.generateSummaryReport();

        System.out.println("==================================================");
    }
}
