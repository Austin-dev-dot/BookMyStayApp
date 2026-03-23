public class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400.0, 150.0);
    }

    @Override
    public String getRoomName() {
        return "Double Room";
    }
}
