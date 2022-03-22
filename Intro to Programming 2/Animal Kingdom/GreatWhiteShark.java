public class GreatWhiteShark extends Fish implements WaterDweller, Endangered {


    public GreatWhiteShark(int id, String name) {
        super(id, name, BirthType.LIVE_BIRTH);
    }

    public boolean isWarmBlooded() {
        return false;
    }

    public String getDescription()
    {
        return "A cute GreatWhiteShark";
    }

    public void displayConservationInformation() {
        System.out.println("Your Great White Shark "+ getName()+" is endangered");
    }

    public boolean breathesAir() {
        return false;
    }
}
