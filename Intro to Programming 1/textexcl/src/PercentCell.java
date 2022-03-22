public class PercentCell extends RealCell {
    String s;
    double value;
    public PercentCell(String s) {
        value = Double.parseDouble(s.substring(0, s.length()-1))*.01;
        this.s = value+"";
    }

    public String abbreviatedCellText() {
        String x = Double.toString(getDoubleValue() * 100);
        //x = x.substring(0, Math.min(9,x.length())) +"%";//trim to 9chars
        x = x.split("\\.")[0] +"%";
        String temp = String.format(String.format("%%-%d.%ds", 10, 10),  x);
        return temp;
    }

    public String fullCellText() {
        return s;
    }

    public double getDoubleValue() {
        return value;
    }
}
