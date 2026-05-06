package artemis.simulator.model.material.rocket;

import artemis.simulator.model.material.booster.Booster;
import artemis.simulator.model.material.capsule.Capsule;
import artemis.simulator.model.material.launcher.Launcher;

import java.util.List;
import java.util.ArrayList;

public class Rocket {

    private final Launcher launcher;
    private final Capsule capsule;
    private final List<Booster> boosters;

    public Rocket(Launcher launcher, Capsule capsule) {
        this.launcher = launcher;
        this.capsule = capsule;
        this.boosters = new ArrayList<>();
    }

    public void addBooster(Booster booster) {
        this.boosters.add(booster);
    }

    public Capsule getCapsule() {
        return this.capsule;
    }

    public long getTotalPrice() {
        long total = this.launcher.getPrice() + this.capsule.getPrice();
        for (Booster b : this.boosters) {
            total += b.getPrice();
        }
        return total;
    }

    public double getTotalWeight() {
        double totalWeight = this.launcher.getWeight() + this.capsule.getWeight();
        for (Booster b : this.boosters) {
            totalWeight += b.getWeight();
        }
        return totalWeight;
    }

    public double getTotalThrust() {
        double totalThrust = this.launcher.getThrust();
        for (Booster b : this.boosters) {
            totalThrust += b.getThrust();
        }
        return totalThrust;
    }
}