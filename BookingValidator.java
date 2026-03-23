public class BookingValidator {
    public static void validateAvailability(String roomType, RoomInventory inventory) throws InvalidBookingException {
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty.");
        }
        
        int available = inventory.getAvailableRooms(roomType);
        if (available <= 0) {
            throw new InvalidBookingException("No availability for room type: " + roomType);
        }
    }
}
