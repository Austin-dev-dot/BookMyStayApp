public abstract class Room {
    protected int bedCount;
    protected double sizeSquareFeet;
    protected double pricePerNight;

    public Room(int bedCount, double sizeSquareFeet, double pricePerNight) {
        this.bedCount = bedCount;
        this.sizeSquareFeet = sizeSquareFeet;
        this.pricePerNight = pricePerNight;
    }

    public int getBedCount() {
        return bedCount;
    }

    public double getSizeSquareFeet() {
        return sizeSquareFeet;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public abstract String getRoomName();

    @Override
    public String toString() {
        return getRoomName() + " [Beds=" + bedCount + ", Size=" + sizeSquareFeet + " sqft, Price=$" + pricePerNight + "/night]";
    }
}
