package artemis.simulator.model.mission;

import artemis.simulator.model.material.rocket.Rocket;

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

    public void runSimulation(Rocket rocket) {
        System.out.println("\n--- SIMULATION LOG ---");
        System.out.println("Mission: " + this.name);
        
        if (this.isCrewed && !rocket.getCapsule().isCrewed()) {
            System.out.println("Critical failure: The mission requires a crew, but the capsule is uncrewed.");
            return;
        }

        if (rocket.getTotalThrust() > rocket.getTotalWeight()) {
            System.out.println("Liftoff successful! The rocket leaves the launch pad.");
        } else {
            System.out.println("Critical failure: Thrust is insufficient for liftoff.");
        }
    }
}