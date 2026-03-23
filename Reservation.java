public class Reservation {
    private String guestName;
    private Room requestedRoom;
    private String reservationId;
    private String allocatedRoomId;

    public Reservation(String guestName, Room requestedRoom) {
        this.guestName = guestName;
        this.requestedRoom = requestedRoom;
        this.reservationId = java.util.UUID.randomUUID().toString().substring(0, 8);
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRequestedRoom() {
        return requestedRoom;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getAllocatedRoomId() {
        return allocatedRoomId;
    }

    public void setAllocatedRoomId(String allocatedRoomId) {
        this.allocatedRoomId = allocatedRoomId;
    }

    @Override
    public String toString() {
        return "Reservation Request [" + reservationId + ": Guest=" + guestName + ", Room=" + requestedRoom.getRoomName() + "]";
    }
}
