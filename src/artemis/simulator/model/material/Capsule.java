package artemis.simulator.model.material.capsule;

public abstract class Capsule {
    
    private final String name;
    private final boolean isCrewed;
    private final int maxOccupants;
    private final double weight;
    private final long price;

    public Capsule(String name, boolean isCrewed, int maxOccupants, double weight, long price) {
        this.name = name;
        this.isCrewed = isCrewed;
        this.maxOccupants = maxOccupants;
        this.weight = weight;
        this.price = price;
    }

    public String getName() { return name; }
    public boolean isCrewed() { return isCrewed; }
    public int getMaxOccupants() { return maxOccupants; }
    public double getWeight() { return weight; }
    public long getPrice() { return price; }
}