public class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250.0, 100.0);
    }

    @Override
    public String getRoomName() {
        return "Single Room";
    }
}
