import java.util.List;

public class SearchService {
    
    public void searchAvailableRooms(List<Room> rooms, RoomInventory inventory) {
        System.out.println("--- Search Results: Available Rooms ---");
        boolean found = false;
        
        for (Room room : rooms) {
            int availableCount = inventory.getAvailableRooms(room.getRoomName());
            if (availableCount > 0) {
                System.out.println(room.toString() + " | Available: " + availableCount);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No rooms are currently available.");
        }
        System.out.println("---------------------------------------");
    }
}
