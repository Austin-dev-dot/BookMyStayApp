import java.util.HashMap;
import java.util.Map;

public class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void initializeInventory(String roomName, int count) {
        inventory.put(roomName, count);
    }

    public int getAvailableRooms(String roomName) {
        return inventory.getOrDefault(roomName, 0);
    }

    public void updateInventory(String roomName, int delta) {
        int current = getAvailableRooms(roomName);
        inventory.put(roomName, current + delta);
    }

    public void displayInventory() {
        System.out.println("--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
        System.out.println("------------------------------");
    }
}
