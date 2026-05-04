package artemis.simulator.model.material.booster;

public class Booster {
    
    private final String name;
    private final double thrust; 
    private final double weight; 
    private final long price;

    public Booster(String name, double thrust, double weight, long price) {
        this.name = name;
        this.thrust = thrust;
        this.weight = weight;
        this.price = price;
    }
    
    public String getName() { return name; }
    public double getThrust() { return thrust; }
    public double getWeight() { return weight; }
    public long getPrice() { return price; }
}