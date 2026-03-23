import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

public class BookingService {
    private RoomInventory inventory;
    private Set<String> globalAllocatedRoomIds;
    private Map<String, Set<String>> roomTypeAllocations;
    private BookingHistory history;

    public BookingService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
        this.globalAllocatedRoomIds = new HashSet<>();
        this.roomTypeAllocations = new HashMap<>();
    }

    public void processQueue(Queue<Reservation> queue) {
        System.out.println("\n--- Processing Booking Queue ---");
        while (!queue.isEmpty()) {
            Reservation request = queue.poll();
            String roomType = request.getRequestedRoom().getRoomName();

            try {
                BookingValidator.validateAvailability(roomType, inventory);
                
                // Generate unique room ID
                String roomId;
                do {
                    roomId = roomType.substring(0, 3).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 5);
                } while (globalAllocatedRoomIds.contains(roomId));
                
                // Assign room and record ID
                globalAllocatedRoomIds.add(roomId);
                roomTypeAllocations.putIfAbsent(roomType, new HashSet<>());
                roomTypeAllocations.get(roomType).add(roomId);

                // Update inventory and history
                inventory.updateInventory(roomType, -1);
                request.setAllocatedRoomId(roomId);
                history.recordBooking(request);
                
                System.out.println("Confirmed: " + request.getGuestName() + " allocated to " + roomType + " (Room ID: " + roomId + ")");
            } catch (InvalidBookingException e) {
                System.out.println("Validation Error: " + request.getGuestName() + " booking failed. " + e.getMessage());
            }
        }
        System.out.println("--------------------------------");
    }

    public void displayAllocations() {
        System.out.println("--- Current Room Allocations ---");
        for (Map.Entry<String, Set<String>> entry : roomTypeAllocations.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("--------------------------------");
    }
}
