// Update this file with your own code.

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class Spreadsheet implements Grid
{
	Cell[][] matrix = new Cell[20][12];
	public Spreadsheet(){
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[i].length; j++){
				matrix[i][j] = new EmptyCell();
			}
		}
	}
	public Cell[] getArr(Location l1, Location l2){
		int r1 = l1.getRow();
		int r2 = l2.getRow();
		int c1 = l1.getCol();
		int c2 = l2.getCol();
		ArrayList<Cell> arr = new ArrayList<>();
		for(int i=r1; i<=r2; i++){
			for(int j=c1; j<=c2; j++){
				arr.add(getCell(i, j));
			}
		}
		return arr.toArray(new Cell[0]);
	}
	public Cell[] helper(String range){
		String parse[] = range.split("-");
		SpreadsheetLocation l1 = new SpreadsheetLocation(parse[0]);
		SpreadsheetLocation l2 = new SpreadsheetLocation(parse[1]);
		return getArr(l1, l2);
	}
	public void fillSort(Cell[] arr, String range){
		String parse[] = range.split("-");
		SpreadsheetLocation l1 = new SpreadsheetLocation(parse[0]);
		SpreadsheetLocation l2 = new SpreadsheetLocation(parse[1]);
		int r1 = l1.getRow();
		int r2 = l2.getRow();
		int c1 = l1.getCol();
		int c2 = l2.getCol();
		int idx = 0;
		for(int i=r1; i<=r2; i++){
			for(int j=c1; j<=c2; j++){
				matrix[i][j] = arr[idx];
				idx++;
			}
		}
	}
	public String processSort(String command){
		command = command.toUpperCase();
		String[] job = command.split(" ");
		command = job[0];
		Cell[] arr = helper(job[1]);
		if(command.equals("SORTA") || command.equals("SORTD")) {
			try{
				TextCell[] temp = new TextCell[arr.length];
				for(int i=0; i<arr.length; i++){
					temp[i] = (TextCell) arr[i];
				}
				sort(temp);
				arr = temp;
			}
			catch(ClassCastException e){
				System.out.println("LOL1");
			}
			try{
				RealCell[] temp = new RealCell[arr.length];
				for(int i=0; i<arr.length; i++){
					temp[i] = (RealCell) arr[i];
				}
				sort(temp);
				arr=temp;
			}
			catch(ClassCastException e){
				System.out.println("LOL2");
			}
		}
		if(command.equals("SORTD")) {
			Spreadsheet.reverse(arr);
		}
		fillSort(arr, job[1]);
		return getGridText();
	}
	@Override
	public String processCommand(String command)
	{
		if(command.equals("")) return "";
		if(command.toLowerCase().startsWith("sort")){
			return processSort(command);
		}
		String[] x= command.split(" ");
		if(x.length>=3){
			for(int i=3; i<x.length; i++){
				x[2]= x[2] +" "+ x[i];
			}
			return cellAssign(x);
		}
		switch (x.length){
			case 2:
				return clearCell(x[1]);
			case 1:
				if(x[0].toLowerCase().equals("clear")) {
					return clear();
				}
				return cellInspection(x[0]);
			default:
				return "";
		}
	}
	public String cellInspection(String pos){
		pos = pos.toUpperCase();
		int col = pos.charAt(0)-'A';
		int row = Integer.parseInt(pos.substring(1))-1;
		return matrix[row][col].fullCellText();
	}
	public int classify(String x){
		if(x.endsWith("\"")) return 0;//text cell
		if(x.endsWith("%")) return 2;//percent
		if(x.endsWith(")")) return 3;//formula
		return 1;//value
	}
	public String cellAssign(String[] command){
		String pos = command[0];
		pos = pos.toUpperCase();
		String operator = command[1];
		String to = command[2];
		int col = pos.charAt(0)-'A';
		int row = Integer.parseInt(pos.substring(1))-1;
		int type = classify(to);
		Cell res= new EmptyCell();
		switch (type){
			case 0:
				res = new TextCell(to.substring(1, to.length()-1));//remove quotes
				break;
			case 1:
				res = new ValueCell(to);
				break;
			case 2:
				res = new PercentCell(to);
				break;
			case 3:
				res = new FormulaCell(to, this );
				break;

		}
		matrix[row][col]= res;
		return getGridText();
	}
	public String clear(){
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[i].length; j++){
				matrix[i][j] = new EmptyCell();
			}
		}
		return getGridText();
	}
	public String clearCell(String pos){
		pos = pos.toUpperCase();
		int col = pos.charAt(0)-'A';
		int row = Integer.parseInt(pos.substring(1))-1;
		matrix[row][col] = new EmptyCell();
		return getGridText();
	}
	@Override
	public int getRows()
	{
		return matrix.length;
	}

	@Override
	public int getCols()
	{
		if(matrix.length==0) return 0;
		return matrix[0].length;
	}

	@Override
	public Cell getCell(Location loc)
	{
		int r = loc.getRow();
		int c = loc.getCol();
		return matrix[r][c];
	}
	public Cell getCell(int row, int col){
		return matrix[row][col];
	}

	@Override
	public String getGridText()
	{
		int N = matrix.length;
		int M = matrix[0].length;
		String ret ="";
		ret+=String.format("%-3s", "");
		for (int j=0; j<M; j++) {
			ret+= addWhiteSpace(Character.toString('A'+j));
		}
		ret+="|\n";
		for(int i=0; i<N; i++){
			ret+= String.format("%-3d", i+1);
			for(int j=0; j<M; j++){
				ret+=addWhiteSpace(matrix[i][j].abbreviatedCellText());
			}
			ret+="|";
			ret+="\n";
		}
		return ret;
	}
	public String addWhiteSpace(String s){
		String x = s;
		while(x.length()<10){
			x=x+" ";
		}
		x = "|"+x;
		return x;
	}

	public static void sort(TextCell[] arr){
		int N = arr.length;
		for(int i=0; i<N; i++){
			for(int j=0; j<N-1; j++){
				if(arr[j].compareTo(arr[j+1]) > 0){
					TextCell temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	public static void sort(RealCell[] arr){
		int N = arr.length;
		for(int i=0; i<N; i++){
			for(int j=0; j<N-1; j++){
				if(arr[j].compareTo(arr[j+1]) > 0){
					RealCell temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	public static void reverse(Cell[] arr){
		int left = 0;
		int right = arr.length-1;
		while(left<right){
			Cell temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++; right--;
		}
	}

}
