package artemis.simulator.model.material.launcher;

public abstract class Launcher {
    private final String name;
    private final boolean isCrewed;
    private final double maxFuel;
    private final int maxBooster;
    private final double payloadCapacity;
    private final long price;
    
    public Launcher(String name, boolean isCrewed, double maxFuel, int maxBooster, double payloadCapacity, long price) {
        this.name = name;
        this.isCrewed = isCrewed;
        this.maxFuel = maxFuel;
        this.maxBooster = maxBooster;
        this.payloadCapacity = payloadCapacity;
        this.price = price;
    }
    
    public String getName() { return name; }
    public boolean isCrewed() { return isCrewed; }
    public double getmaxFuel() { return maxFuel; }
    public int getmaxBooster() { return maxBooster; }
    public double getpayloadCapacity() { return payloadCapacity; }
    public long getprice() { return price; }
}