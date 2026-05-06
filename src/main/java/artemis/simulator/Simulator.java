package artemis.simulator;

import artemis.simulator.exception.InsufficientFuelException;
import artemis.simulator.model.material.booster.Booster;
import artemis.simulator.model.material.capsule.*;
import artemis.simulator.model.material.launcher.*;
import artemis.simulator.model.material.rocket.Rocket;
import artemis.simulator.model.mission.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulator {

    private static final List<Booster> boosterCatalog = new ArrayList<>();
    private static final List<Launcher> launcherCatalog = new ArrayList<>();
    private static final List<Capsule> capsuleCatalog = new ArrayList<>();
    private static final List<Mission> missionCatalog = new ArrayList<>();
    private static final List<String> launchHistory = new ArrayList<>();
    
    private static Launcher selectedLauncher = null;
    private static Capsule selectedCapsule = null;
    private static Mission selectedMission = null;
    private static final List<Booster> selectedBoosters = new ArrayList<>();

    public static void main(String[] args) {
        boosterCatalog.add(new Booster("EAP (Ariane)", 6470.0, 270.0, 30));
        boosterCatalog.add(new Booster("SRB (Shuttle)", 12500.0, 590.0, 55));
        boosterCatalog.add(new Booster("BE-3", 490.0, 25.0, 12));

        launcherCatalog.add(new Ariane5());
        launcherCatalog.add(new Falcon9());
        launcherCatalog.add(new SLS());
        launcherCatalog.add(new SaturnV());

        capsuleCatalog.add(new Apollo());
        capsuleCatalog.add(new CargoDragon());
        capsuleCatalog.add(new Crewdragon());
        capsuleCatalog.add(new Orion());

        missionCatalog.add(new Moon());
        missionCatalog.add(new Mars());
        missionCatalog.add(new ISS());
        missionCatalog.add(new EarthOrbit());
        missionCatalog.add(new AsteroidMining());

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the ARTEMIS Launch Center!");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Configure Rocket");
            System.out.println("2. Choose Mission");
            System.out.println("3. Launch Simulation");
            System.out.println("4. View History");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    configureRocket(scanner);
                    break;
                case "2":
                    chooseMission(scanner);
                    break;
                case "3":
                    if (selectedLauncher == null || selectedCapsule == null || selectedMission == null) {
                        System.out.println("Configuration incomplete. Please select a launcher, a capsule, and a mission first.");
                    } else {
                        Rocket rocket = new Rocket(selectedLauncher, selectedCapsule);
                        for (Booster b : selectedBoosters) {
                            rocket.addBooster(b);
                        }

                        try {
                            rocket.performPreFlightChecks(selectedMission);
                            selectedMission.runSimulation(rocket);
                            
                            String record = "SUCCESS | Mission: " + selectedMission.getName() + " | Launcher: " + selectedLauncher.getName() + " | Capsule: " + selectedCapsule.getName() + " | Boosters: " + selectedBoosters.size();
                            launchHistory.add(record);
                            System.out.println("Launch sequence completed successfully.");
                            
                        } catch (InsufficientFuelException e) {
                            String record = "FAILURE | Mission: " + selectedMission.getName() + " | Reason: " + e.getMessage();
                            launchHistory.add(record);
                            System.out.println("ABORT: " + e.getMessage());
                        }
                    }
                    break;
                case "4":
                    System.out.println("\n--- LAUNCH HISTORY ---");
                    if (launchHistory.isEmpty()) {
                        System.out.println("No launches recorded yet.");
                    } else {
                        for (int i = 0; i < launchHistory.size(); i++) {
                            System.out.println((i + 1) + ". " + launchHistory.get(i));
                        }
                    }
                    break;
                case "5":
                    System.out.println("Exiting simulator...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void configureRocket(Scanner scanner) {
        System.out.println("\n--- ROCKET CONFIGURATION ---");
        
        System.out.println("Available Launchers:");
        for (int i = 0; i < launcherCatalog.size(); i++) {
            System.out.println((i + 1) + ". " + launcherCatalog.get(i).getName());
        }
        System.out.print("Select a launcher: ");
        try {
            int launcherIndex = Integer.parseInt(scanner.nextLine()) - 1;
            selectedLauncher = launcherCatalog.get(launcherIndex);
        } catch (Exception e) {
            System.out.println("Selection error. Launcher not set.");
            return;
        }

        System.out.println("\nAvailable Capsules:");
        for (int i = 0; i < capsuleCatalog.size(); i++) {
            System.out.println((i + 1) + ". " + capsuleCatalog.get(i).getName());
        }
        System.out.print("Select a capsule: ");
        try {
            int capsuleIndex = Integer.parseInt(scanner.nextLine()) - 1;
            selectedCapsule = capsuleCatalog.get(capsuleIndex);
        } catch (Exception e) {
            System.out.println("Selection error. Capsule not set.");
            return;
        }

        selectedBoosters.clear();
        String addMore;
        do {
            System.out.println("\nAvailable Boosters:");
            for (int i = 0; i < boosterCatalog.size(); i++) {
                System.out.println((i + 1) + ". " + boosterCatalog.get(i).getName());
            }
            System.out.print("Select a booster: ");
            try {
                int boosterIndex = Integer.parseInt(scanner.nextLine()) - 1;
                selectedBoosters.add(boosterCatalog.get(boosterIndex));
            } catch (Exception e) {
                System.out.println("Invalid booster selection.");
            }
            
            System.out.print("Add another booster? (y/n): ");
            addMore = scanner.nextLine();
        } while (addMore.equalsIgnoreCase("y"));
    }

    private static void chooseMission(Scanner scanner) {
        System.out.println("\n--- MISSION SELECTION ---");
        for (int i = 0; i < missionCatalog.size(); i++) {
            System.out.println((i + 1) + ". " + missionCatalog.get(i).getName());
        }
        System.out.print("Select a mission: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            selectedMission = missionCatalog.get(index);
            System.out.println("Mission selected: " + selectedMission.getName());
        } catch (Exception e) {
            System.out.println("Invalid mission selection.");
        }
    }
}