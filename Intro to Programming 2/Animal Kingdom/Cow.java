public class Cow extends Mammal implements Endangered {

    public Cow(int id, String name) {
        super(id, name, BirthType.LIVE_BIRTH);
    }

    public boolean isWarmBlooded() {
        return true;
    }


    public String getDescription() {
        return "Your cute cow "+getName();
    }

    public void displayConservationInformation() {
        System.out.println("Your Cute Cow "+getName()+" is endangered by Farmer John");
    }
}
