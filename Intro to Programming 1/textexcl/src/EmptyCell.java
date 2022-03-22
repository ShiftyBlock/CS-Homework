public class EmptyCell implements Cell {
    String s;
    public EmptyCell(){
        s = "";
    }
    public String abbreviatedCellText(){
        return String.format(String.format("%%-%d.%ds", 10, 10),  s);
    }
    public String fullCellText() {
        return s;
    }
}
