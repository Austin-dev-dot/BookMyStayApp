import java.util.Stack;

public class CancellationService {
    private RoomInventory inventory;
    private BookingHistory history;
    private Stack<String> releasedRoomIds;

    public CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
        this.releasedRoomIds = new Stack<>();
    }

    public void cancelBooking(Reservation reservation) {
        System.out.println("\n--- Processing Cancellation ---");
        
        if (reservation == null || reservation.getAllocatedRoomId() == null) {
            System.out.println("Cancellation Failed: Invalid or unconfirmed reservation.");
            return;
        }

        if (!history.getConfirmedBookings().contains(reservation)) {
            System.out.println("Cancellation Failed: Reservation not found in history.");
            return;
        }

        String roomId = reservation.getAllocatedRoomId();
        String roomType = reservation.getRequestedRoom().getRoomName();

        // 1. Record in rollback structure
        releasedRoomIds.push(roomId);

        // 2. Increment inventory
        inventory.updateInventory(roomType, 1);

        // 3. Update history
        history.removeBooking(reservation);

        System.out.println("Cancelled: " + reservation.getGuestName() + "'s booking for " + roomType);
        System.out.println("Released Room ID " + roomId + " added to rollback stack.");
        System.out.println("-------------------------------");
    }

    public void displayRollbackStack() {
        System.out.println("Recently Released Room IDs: " + releasedRoomIds);
    }
}
