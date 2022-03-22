public class BelugaWhale extends Whale  {
    public BelugaWhale(int id, String name) {
        super(id, name, BirthType.LIVE_BIRTH);
    }

    public boolean isWarmBlooded() {
        return true;
    }

    public String getDescription()
    {
        return "A cute BelugaWhale";
    }

    public boolean breathesAir() {
        return true;
    }
}
