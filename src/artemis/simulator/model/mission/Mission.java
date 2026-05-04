package artemis.simulator.model.mission;

public abstract class Mission {

    private final String name;
    private final boolean isCrewed;
    private final long distance;
    private final String time;
    private final double fuelCoefficient;

    public Mission(String name, boolean isCrewed, long distance, String time, double fuelCoefficient) {
        this.name = name;
        this.isCrewed = isCrewed;
        this.distance = distance;
        this.time = time;
        this.fuelCoefficient = fuelCoefficient;
    }

    public String getName() { return name; }
    public boolean isCrewed() { return isCrewed; }
    public long getDistance() { return distance; }
    public String getTime() { return time; }
    public double getFuelCoefficient() { return fuelCoefficient; }
}
