public class Reservation {
    private String guestName;
    private Room requestedRoom;
    private String reservationId;

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

    @Override
    public String toString() {
        return "Reservation Request [" + reservationId + ": Guest=" + guestName + ", Room=" + requestedRoom.getRoomName() + "]";
    }
}
