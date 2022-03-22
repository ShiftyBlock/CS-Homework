public class Ostrich extends Bird implements Winged{

    public Ostrich(int id, String name) {
        super(id, name, BirthType.LAYS_EGGS);
    }

    public boolean isWarmBlooded() {
        return true;
    }
    public String getDescription()
    {
        return "A cute Ostrich";
    }
    public boolean canFly() {
        return false;
    }
}
