import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingHistory {
    private List<Reservation> confirmedBookings;

    public BookingHistory() {
        this.confirmedBookings = new ArrayList<>();
    }

    public void recordBooking(Reservation reservation) {
        confirmedBookings.add(reservation);
    }

    public List<Reservation> getConfirmedBookings() {
        return Collections.unmodifiableList(confirmedBookings);
    }
}
