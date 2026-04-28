package artemis.simulator.model.material;

import artemis.simulator.model.material.capsule.Capsule;

public class Launcher {

    private final String name;
    private final Capsule capsule;

    public Launcher(String name, Capsule capsule) {
        this.name = name;
        this.capsule = capsule;
    }
}