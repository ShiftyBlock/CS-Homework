public class CaliforniaCondor extends Bird implements Winged, Endangered {

    public CaliforniaCondor(int id, String name) {
        super(id, name, BirthType.LAYS_EGGS);
    }

    public boolean isWarmBlooded() {
        return true;
    }

    public String getDescription()
    {
        return "A cute California Condor";
    }

    public void displayConservationInformation() {
        System.out.println("Your CaliforniaCondor "+ getName()+" is endangered");
    }
    public boolean canFly() {
        return true;
    }
}
