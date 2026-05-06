package artemis.simulator.model.material.rocket;

import artemis.simulator.exception.InsufficientFuelException;
import artemis.simulator.exception.MannedMissionMismatchException;
import artemis.simulator.exception.PayloadExceededException;
import artemis.simulator.exception.TechnicalAnomalyException;
import artemis.simulator.exception.TooManyBoostersException;
import artemis.simulator.model.material.booster.Booster;
import artemis.simulator.model.material.capsule.Capsule;
import artemis.simulator.model.material.launcher.Launcher;
import artemis.simulator.model.mission.Mission;
import artemis.simulator.util.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void performPreFlightChecks(Mission mission) throws InsufficientFuelException, PayloadExceededException, TooManyBoostersException, MannedMissionMismatchException {
        if (this.boosters.size() > this.launcher.getMaxBoosters()) {
            throw new TooManyBoostersException("Too many boosters.");
        }

        if (getTotalWeight() > this.launcher.getPayloadCapacity()) {
            throw new PayloadExceededException("Payload exceeded.");
        }

        if (mission.isCrewed() && (!this.capsule.isCrewed() || this.capsule.getMaxOccupants() == 0)) {
            throw new MannedMissionMismatchException("Capsule incompatible with a manned mission.");
        }

        double requiredFuel = calculateRequiredFuel(mission);
        double maxCapacity = this.launcher.getMaxFuelCapacity();

        if (requiredFuel > maxCapacity) {
            throw new InsufficientFuelException("Insufficient fuel.");
        }
    }

    public void launch() throws TechnicalAnomalyException {
        Random random = new Random();
        if (random.nextDouble() < Configuration.PROBABILITY_PROBLEM) {
            throw new TechnicalAnomalyException("Unexpected technical anomaly.");
        }
    }

    public double calculateRequiredFuel(Mission mission) {
        return (getTotalWeight() * mission.getDistance() * mission.getFuelCoefficient()) / 1000.0;
    }

    public double calculateTotalLaunchCost(Mission mission) {
        return getTotalPrice() + (calculateRequiredFuel(mission) * Configuration.PRICE_KEROSENE_IN_TONNE);
    }
}