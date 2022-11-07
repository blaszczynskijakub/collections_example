public class Truck extends Car{
    private int capacity;

    public Truck(String brand, int price, int productionYear, int capacity) {
        super(brand, price, productionYear);
        this.capacity = capacity;
    }
}
