package artemis.simulator.model.material;

import artemis.simulator.model.material.capsule.Capsule;
import java.util.List;
import java.util.ArrayList;

public class Launcher {

    private final String name;
    private final Capsule capsule;
    private final List<Booster> boosters;

    public Launcher(String name, Capsule capsule) {
        this.name = name;
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

    
}