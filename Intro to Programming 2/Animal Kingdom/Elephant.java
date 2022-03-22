public class Elephant extends Mammal implements Endangered {

    public Elephant(int id, String name) {
        super(id, name, BirthType.LIVE_BIRTH);
    }

    public boolean isWarmBlooded() {
        return true;
    }


    public String getDescription()
    {
        return "A cute Elephant";
    }

    public void displayConservationInformation() {
        System.out.println("Your Elephant: "+ getName()+" is endangered");
    }
}
