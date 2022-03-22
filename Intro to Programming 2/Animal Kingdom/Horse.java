public class Horse extends Mammal {

    public Horse(int id, String name) {
        super(id, name, BirthType.LIVE_BIRTH);

    }

    public boolean isWarmBlooded() {
        return true;
    }

    public String getDescription()
    {
        return "A cute Horse";
    }
}
