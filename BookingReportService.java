import java.util.List;

public class BookingReportService {
    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    public void generateSummaryReport() {
        List<Reservation> bookings = history.getConfirmedBookings();
        System.out.println("\n--- Booking Summary Report ---");
        System.out.println("Total Confirmed Bookings: " + bookings.size());
        for (int i = 0; i < bookings.size(); i++) {
            Reservation r = bookings.get(i);
            System.out.println((i + 1) + ". " + r.getGuestName() + " | Room: " + r.getRequestedRoom().getRoomName() + " | ID: " + r.getReservationId());
        }
        System.out.println("------------------------------");
    }
}
