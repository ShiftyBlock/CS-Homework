public class TextCell implements Cell, Comparable<TextCell>{
    private String s;
    public TextCell(String s){
        this.s=s;
    }
    @Override
    public String abbreviatedCellText() {
        String x =s.substring(0, Math.min(s.length(), 10));
        return String.format(String.format("%%-%d.%ds", 10, 10),  s);
    }

    @Override
    public String fullCellText() {
        return "\""+s+"\"";
    }

    public String getS(){
        return this.s;
    }
    @Override
    public int compareTo(TextCell obj) {
        return s.compareTo(obj.getS());
    }
}
