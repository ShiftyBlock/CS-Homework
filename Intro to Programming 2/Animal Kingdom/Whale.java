public abstract class Whale extends Mammal {
    public Whale(int id, String name, BirthType birthType) {
        super(id, name, birthType);
    }

    public boolean isWarmBlooded() {
        return false;
    }

    public String getDescription() {
        return null;
    }
}
