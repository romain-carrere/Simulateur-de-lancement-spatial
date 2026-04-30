package artemis.simulator.model.material.launcher;

public abstract class Launcher {
    private final boolean isCrewed;
    private final double maxFuel;
    private final int maxBooster;
    private final double payloadCapacity;
    private final long price;
    
    public Launcher(boolean isCrewed, double maxFuel, int maxBooster, double payloadCapacity, long price) {
        this.isCrewed = isCrewed;
        this.maxFuel = maxFuel;
        this.maxBooster = maxBooster;
        this.payloadCapacity = payloadCapacity;
        this.price = price;
    }
}
