public class Reservation {
    private String guestName;
    private Room requestedRoom;

    public Reservation(String guestName, Room requestedRoom) {
        this.guestName = guestName;
        this.requestedRoom = requestedRoom;
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRequestedRoom() {
        return requestedRoom;
    }

    @Override
    public String toString() {
        return "Reservation Request [Guest=" + guestName + ", Room=" + requestedRoom.getRoomName() + "]";
    }
}
