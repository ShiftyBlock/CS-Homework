//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
    private char c;
    private int r;
    @Override
    public int getCol()
    {
        return c-'A';
    }

    @Override
    public int getRow()
    {
        return r;
    }
    
    public SpreadsheetLocation(String cellName)
    {
        c = cellName.charAt(0);
        r = Integer.parseInt(cellName.substring(1))-1;
    }

}
