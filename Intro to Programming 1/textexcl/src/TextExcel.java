import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Update this file with your own code.
//DRIVER CODE

public class TextExcel
{

	public static void main(String[] args)
	{
		Spreadsheet spreadsheet = new Spreadsheet();
		Scanner sc= new Scanner(System.in);
		String line = sc.nextLine();
		ArrayList<TextCell> arr = new ArrayList<>();
		while(!line.equals("quit")){
			arr.add(new TextCell(line));
			line = sc.nextLine();
		}
		TextCell[] a = arr.toArray(new TextCell[0]);
		Spreadsheet.sort(a);
		for(TextCell x: a){
			System.out.println(x.fullCellText());
		}
		/*
		int i=0;
		while(!line.equals("quit")){
			//System.out.println(spreadsheet.processCommand(line));
			line = line.substring(0, Math.min(9,line.length())) +"%";

			String temp = String.format(String.format("%%-%d.%ds", 10, 10),  line);
			System.out.println(temp);
			line = sc.nextLine();
		}
		System.out.println(spreadsheet.getGridText());
		 */

	}
}
