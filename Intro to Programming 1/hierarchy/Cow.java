public class Cow extends Mammal{
    public Cow(int id, String name) {
        super(id, name, BirthType.LIVE_BIRTH);

    }

    public boolean isWarmBlooded() {
        return true;
    }
    public void goMoo(){
        System.out.println("MOOOOOOOOOOOOOOOOO");
    }
    public String getDescription() {
        return "A cute cow";
    }
}
