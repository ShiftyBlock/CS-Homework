public abstract class Bird extends Animal{
    public Bird(int id, String name, BirthType birthType) {
        super(id, name, birthType);
    }

    public boolean isWarmBlooded() {
        return true;
    }
}
