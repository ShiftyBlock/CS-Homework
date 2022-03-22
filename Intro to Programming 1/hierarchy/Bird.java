public abstract class Bird extends Animal implements Winged{
    public Bird(int id, String name, BirthType birthType) {
        super(id, name, birthType);
    }

    public boolean isWarmBlooded() {
        return true;
    }
}
