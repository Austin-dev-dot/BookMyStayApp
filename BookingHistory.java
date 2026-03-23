import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.Serializable;

public class BookingHistory implements Serializable {
    private List<Reservation> confirmedBookings;

    public BookingHistory() {
        this.confirmedBookings = new ArrayList<>();
    }

    public void recordBooking(Reservation reservation) {
        confirmedBookings.add(reservation);
    }

    public void removeBooking(Reservation reservation) {
        confirmedBookings.remove(reservation);
    }

    public List<Reservation> getConfirmedBookings() {
        return Collections.unmodifiableList(confirmedBookings);
    }
}
