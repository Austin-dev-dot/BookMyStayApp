import java.util.HashMap;
import java.util.Map;

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 6);
        inventory.put("Suite Room", 3);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type does not exist in inventory.");
        }
    }

    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("        Book My Stay App");
        System.out.println("   Hotel Booking Management System");
        System.out.println("           Version 3.1");
        System.out.println("======================================\n");

        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory();

        System.out.println("\nChecking availability of Double Room...");
        int available = inventory.getAvailability("Double Room");
        System.out.println("Double Room Available: " + available);

        System.out.println("\nUpdating availability after booking...");
        inventory.updateAvailability("Double Room", available - 1);

        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();

        System.out.println("\nApplication finished successfully.");
    }
}
