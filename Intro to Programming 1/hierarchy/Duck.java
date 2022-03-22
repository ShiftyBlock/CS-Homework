import javax.accessibility.Accessible;

public class Duck extends Bird implements Aquatic {

    public Duck(int id, String name) {
        super(id, name, BirthType.LAYS_EGGS);
    }

    public boolean isWarmBlooded() {
        return true;
    }
    public String getDescription()
    {
        return "A cute Duck";
    }
    public boolean canFly() {
        return true;
    }
    public boolean breathesAir(){return true;}
    public String quack(){
        return "quack quack i like crackers";
    }
}
