package artemis.simulator.model.material.booster;

public class Booster {
    
    private final String name;
    private final long thrust;
    private final long weight;
    private final long price;

    public Booster(String name, long thrust, long weight, long price) {
        this.name = name;
        this.thrust = thrust;
        this.weight = weight;
        this.price = price;
    }
    
    public String getName() { return name; }
    public long getThrust() { return thrust; }
    public long getWeight() { return weight; }
    public long getPrice() { return price; }
}