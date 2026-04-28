package artemis.simulator.model.material;

public enum Booster {
    EAP(6470, 270, 30),
    SRB(12500, 590, 55),
    BE3(490, 25, 12);

    private final long pushAdd;
    private final long wheight;
    private final long priceM;

    private Booster(long pushAdd, long wheight, long priceM) {
        this.pushAdd = pushAdd;
        this.wheight = wheight;
        this.priceM = priceM;
    }
    
    public long getpushAdd() { return pushAdd; }
    public long getwheight() { return wheight; }
    public long getpriceM() { return priceM; }

}
