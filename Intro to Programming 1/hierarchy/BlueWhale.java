public class BlueWhale extends Whale {

    public BlueWhale(int id, String name) {
        super(id, name, BirthType.LIVE_BIRTH);
    }

    public boolean isWarmBlooded() {
        return true;
    }

    public String getDescription()
    {
        return "A cute BlueWhale";
    }

    public void displayConservationInformation()
    {
        System.out.println("Your BlueWhale: "+getName()+" is endangered");
    }

    public boolean breathesAir() {
        return true;
    }
}
