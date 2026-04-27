package artemis.simulator.util;

public enum TypeMission {
    ORBITE_TERRESTRE(false, 400, 1.0),
    ISS(true, 400, 1.2),
    LUNE(true, 400000, 0.005),
    MARS(true, 225000000, 0.000015),
    MISSION_PERSONNELLE(false, 1500000, 0.02);

    private final boolean forcedlive;
    private final long distance;
    private final double coeffuel;

    private TypeMission(boolean forcedlive, long distance, double coeffuel) {
        this.forcedlive = forcedlive;
        this.distance = distance;
        this.coeffuel = coeffuel;
    }

    
}