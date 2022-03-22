public class Goldfish extends Fish implements WaterDweller{

    public Goldfish(int id, String name) {
        super(id, name, BirthType.LAYS_EGGS);
    }

    public boolean isWarmBlooded() {
        return false;
    }

    public String getDescription()
    {
        return "A cute Goldfish";
    }

    public boolean breathesAir() {
        return false;
    }
}
