import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
    }

    public double calculateAdditionalCost(String reservationId) {
        List<AddOnService> services = reservationServices.getOrDefault(reservationId, new ArrayList<>());
        double totalCost = 0.0;
        for (AddOnService service : services) {
            totalCost += service.getPrice();
        }
        return totalCost;
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = reservationServices.getOrDefault(reservationId, new ArrayList<>());
        if (services.isEmpty()) {
            System.out.println("Reservation " + reservationId + " has no add-on services.");
            return;
        }

        System.out.println("Add-ons for Reservation " + reservationId + ":");
        for (AddOnService service : services) {
            System.out.println(" - " + service.toString());
        }
        System.out.println("Total Additional Cost: $" + calculateAdditionalCost(reservationId));
    }
}
