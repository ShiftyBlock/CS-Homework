public class Parakeet extends Bird implements Winged{

    public Parakeet(int id, String name) {
        super(id, name, BirthType.LAYS_EGGS);
    }

    public boolean isWarmBlooded() {
        return true;
    }
    public String getDescription()
    {
        return "A cute Parakeet";
    }
    public boolean canFly() {
        return true;
    }
}
