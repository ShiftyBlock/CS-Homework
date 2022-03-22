public class ValueCell extends RealCell{
    String s;
    double value;
    public ValueCell(String s){
        this.s = s;
        value = Double.parseDouble(s);
    }
    @Override
    public String abbreviatedCellText() {
        return String.format(String.format("%%-%d.%ds", 10, 10),  value);
    }
    @Override
    public String fullCellText() {
        return s;
    }

    @Override
    public double getDoubleValue() {
        return value;
    }
}
