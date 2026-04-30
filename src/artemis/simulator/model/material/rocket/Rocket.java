package artemis.simulator.model.material.rocket;

import artemis.simulator.model.material.capsule.Capsule;
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

    public double getTotalWeight() {
        double total = capsule.getWeight();
        for (Booster b : boosters) {
            total += b.getWeight();
        }
        return total;
    }

    public long getTotalPrice() {
        long total = capsule.getPrice();
        for (Booster b : boosters) {
            total += b.getPrice();
        }
        return total;
    }
}