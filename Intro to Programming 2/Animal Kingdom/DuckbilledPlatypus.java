public class DuckbilledPlatypus extends Mammal {

    public DuckbilledPlatypus(int id, String name)
    {
        super(id, name, BirthType.LAYS_EGGS);
    }

    public boolean isWarmBlooded() {
        return true;
    }

    public String getDescription()
    {
        return "A cute DuckBilled Platypus";
    }
}
