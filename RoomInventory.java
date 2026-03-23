import java.util.HashMap;
import java.util.Map;

import java.io.Serializable;

public class RoomInventory implements Serializable {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void initializeInventory(String roomName, int count) {
        inventory.put(roomName, count);
    }

    public synchronized int getAvailableRooms(String roomName) {
        return inventory.getOrDefault(roomName, 0);
    }

    public synchronized void updateInventory(String roomName, int delta) {
        int current = getAvailableRooms(roomName);
        inventory.put(roomName, current + delta);
    }

    public synchronized void displayInventory() {
        System.out.println("--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
        System.out.println("------------------------------");
    }
}
