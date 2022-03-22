public abstract class RealCell implements Cell, Comparable<RealCell>{
    String s;
    public abstract double getDoubleValue();
    public String abbreviatedCellText() {
        return String.format(String.format("%%-%d.%ds", 10, 10),  s);
    }
    public String fullCellText() {
        return s;
    }
    @Override
    public int compareTo(RealCell obj){
        double val = this.getDoubleValue() - obj.getDoubleValue();
        if(val<0) return -1;
        if(val>0) return 1;
        return 0;
    }

}
