import java.io.*;

public class PersistenceService {
    private static final String FILE_NAME = "hotel_data.ser";

    public static void saveState(RoomInventory inventory, BookingHistory history) {
        System.out.println("\n--- Saving System State ---");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            oos.writeObject(history);
            System.out.println("State successfully persisted to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Failed to save state: " + e.getMessage());
        }
    }

    public static RoomInventory loadInventory() {
        System.out.println("\n--- Loading System State ---");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            RoomInventory inventory = (RoomInventory) ois.readObject();
            return inventory;
        } catch (Exception e) {
            System.out.println("No saved state found or corrupted. Starting fresh.");
            return null;
        }
    }

    public static BookingHistory loadHistory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ois.readObject(); // Skip inventory
            BookingHistory history = (BookingHistory) ois.readObject();
            System.out.println("State successfully restored from " + FILE_NAME);
            return history;
        } catch (Exception e) {
            // Error already handled in loadInventory
            return null;
        }
    }
}
