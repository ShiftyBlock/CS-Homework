import java.util.ArrayList;
import java.util.Locale;

public class FormulaCell extends RealCell{
    String s;
    Spreadsheet sp;
    public FormulaCell(String s, Spreadsheet sp){
        this.s = s;
        this.sp = sp;
    }

    public double getRectangularSum(Location l1, Location l2){
        int r1 = l1.getRow();
        int r2 = l2.getRow();
        int c1 = l1.getCol();
        int c2 = l2.getCol();
        double sum = 0;
        for(int i=r1; i<=r2; i++){
            for(int j=c1; j<=c2; j++){
                if(sp.getCell(i, j) instanceof RealCell)  sum+=((RealCell)sp.getCell(i, j)).getDoubleValue();
            }
        }
        return sum;
    }
    public int getRectangularCount(Location l1, Location l2){
        int r1 = l1.getRow();
        int r2 = l2.getRow();
        int c1 = l1.getCol();
        int c2 = l2.getCol();
        return (r2-r1+1) * (c2-c1+1);
    }

    public double helper(String range, int type){
        int count = 1;
        String parse[] = range.split("-");
        SpreadsheetLocation l1 = new SpreadsheetLocation(parse[0]);
        SpreadsheetLocation l2 = new SpreadsheetLocation(parse[1]);
        if(type==1) count = getRectangularCount(l1, l2);
        return getRectangularSum(l1, l2)/count;
    }
    public double getReference(String cell){
        if(Character.isAlphabetic(cell.charAt(0))){
            SpreadsheetLocation sl = new SpreadsheetLocation(cell);
            if(sp.getCell(sl) instanceof RealCell) return ((RealCell)sp.getCell(sl)).getDoubleValue();
            return 0;
        }
        return Double.parseDouble(cell);
    }
    public double getDoubleValue() {
        String exp = s.substring(2, s.length()-2);
        exp = exp.toUpperCase();
        String[] parts = exp.split(" ");
        if(parts.length == 1) {
            if(Character.isAlphabetic(parts[0].charAt(0))){
                SpreadsheetLocation sl = new SpreadsheetLocation(parts[0]);
                 return ((RealCell)sp.getCell(sl)).getDoubleValue();
            }
            return Double.parseDouble(parts[0]);
        }
        String range = parts[1];
        if(parts[0].equals("SUM"))return helper (range, 0);
        if(parts[0].equals("AVG")) return helper(range, 1);

        double cur = getReference(parts[0]);
        for(int i=1; i<parts.length-1; i+=2){
            int operation = classify(parts[i]);
            String next = parts[i+1];
            double val = getReference(next);
            switch(operation){
                case 1:
                    cur+=val;
                    break;
                case 2:
                    cur-=val;
                    break;
                case 3:
                    cur*=val;
                    break;
                case 4:
                    cur/=val;
                    break;
            }
        }
        return cur;
    }
    public int classify(String s){
        switch (s){
            case "+":
                return 1;
            case "-":
                return 2;
            case "*":
                return 3;
            case "/":
                return 4;
        }
        return -1;
    }
    public String abbreviatedCellText() {
        return String.format(String.format("%%-%d.%ds", 10, 10),  getDoubleValue());
    }
    public String fullCellText() {
        return s;
    }
}
