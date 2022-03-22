import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ButtonType;
import javafx.stage.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.*;
import java.io.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ClaimsPaidBarChartEC extends Application {

    /* PROJECT: You will set these values based on the data file. */
    private int yAxisMin, yAxisMax; // the min and max of claims paid
    static XYChart.Series claimsPaidByMonth2016 = new XYChart.Series<>();
    static XYChart.Series claimsPaidByMonth2017 = new XYChart.Series<>();
    static XYChart.Series claimsPaidByMonth2018 = new XYChart.Series<>();

    private static final int TICK_INTERVAL = 1000; // used to customize the visual display

    /* PROJECT: Test with all three valid file names and one invalid file name. */
    // private final String fileName = "Disability_Insurance_2016.csv";
   // private final String fileName = "Disability_Insurance_2017.csv";
//    private final String fileName = "Disability_Insurance_2018.csv";
//   private final String fileName = "Disability_Insurance_BadFileName.csv";

// use this file ONLY if completing the extra credit
 private final String fileName = "Disability_Insurance_2016-2018_ExtraCredit.csv";

    @Override
    public void start(Stage stage) {

        /* creates the chart, x-axis, and y-axis */
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        barChart.setTitle("Disability Insurance Claims Paid");
        barChart.setBarGap(3);
        xAxis.setLabel("Month");
        yAxis.setLabel("Claims Paid");
        yAxis.setTickUnit(TICK_INTERVAL);

        /*
         * PROJECT: This is the series for the chart. You will add data to this series.
         */

        boolean success1 = fillData(claimsPaidByMonth2016);
        boolean success2 = fillData(claimsPaidByMonth2017);
        boolean success3 = fillData(claimsPaidByMonth2018);


        /*
         * PROJECT: Once the fillData method is done, you have set the yAxisMin,
         * yAxisMax, and dataLabel variables. This code now updates the display using
         * those values.
         */


        int yAxisDisplayMin = getVisuallyAppealingAxisValue(yAxisMin);
        int yAxisDisplayMax = getVisuallyAppealingAxisValue(yAxisMax);
        yAxis.setLowerBound(yAxisDisplayMin - TICK_INTERVAL);
        yAxis.setUpperBound(yAxisDisplayMax + TICK_INTERVAL);


// adds the data series to the chart, creates a scene, sets the stage, and
// shows!

        barChart.getData().add(claimsPaidByMonth2016);
        barChart.getData().add(claimsPaidByMonth2017);
        barChart.getData().add(claimsPaidByMonth2018);
        Scene scene = new Scene(barChart, 800, 600);
        stage.setScene(scene);

        if (success1 && success2 && success3) {
            stage.show();
        }

    }

    /*
     * PROJECT: Your code goes in this method. I've added comments below to remind
     * you of what this method needs to do.
     */
    private boolean fillData(Series series) {
        /* PROJECT: YOUR CODE GOES HERE! */


        ArrayList<Integer> list2016 = new ArrayList<Integer>();
        ArrayList<Integer> list2017= new ArrayList<Integer>();
        ArrayList<Integer> list2018= new ArrayList<Integer>();
        String filePath = "";
        String finalFilePath = filePath + fileName;
        boolean found = true;
        String monthLabel = " ";
        int monthValue = 0;

// create a Data object to add to the series for each month/value pair:
        //series.getData().add(new Data(monthLabel, monthValue));
        // jacob this was the bug
        class Data {
            private String monthLabel;
            private int monthValue;

            public Data(String monthLabel, int monthValue) {
                this.monthLabel = monthLabel;
                this.monthValue = monthValue;
            }

            public void setMonthLabel(String monthLabel) {
                this.monthLabel = monthLabel;
            }

            public void setMonthValue(int monthValue) {
                this.monthValue = monthValue;
            }
        }

// read in the data file
// get the dataLabel (the year) from the first line of the file
// use a Scanner to parse the remaining lines of the file, which each contain a
// month name and value
        try {
            BufferedReader read = new BufferedReader(new FileReader(finalFilePath));
            String line = read.readLine();
            String[] years= line.split(",");
            // array is now [month, 2016, 2017, 2018]
            claimsPaidByMonth2016.setName(years[1]);
            claimsPaidByMonth2017.setName(years[2]);
            claimsPaidByMonth2018.setName(years[3]);

            Scanner fileScan = new Scanner(new FileInputStream(finalFilePath));

            fileScan.nextLine();

            while (fileScan.hasNext()) {
                String oneLine = fileScan.nextLine();

                Scanner lineScan = new Scanner(oneLine);
                lineScan.useDelimiter(",");


                String month = lineScan.next();
                int month2016= Integer.parseInt(lineScan.next());
                int month2017= Integer.parseInt(lineScan.next());
                int month2018= Integer.parseInt(lineScan.next());
                list2016.add(month2016);
                list2017.add(month2017);
                list2018.add(month2018);

                Data data2016 = new Data(month, month2016);
                data2016.setMonthLabel(month);
                data2016.setMonthValue(month2016);
                Data data2017 = new Data(month, month2017);
                data2017.setMonthLabel(month);
                data2017.setMonthValue(month2017);
                Data data2018 = new Data(month, month2018);
                data2018.setMonthLabel(month);
                data2018.setMonthValue(month2018);

                try {
                    claimsPaidByMonth2016.getData().add(new XYChart.Data(month, month2016));
                    claimsPaidByMonth2017.getData().add(new XYChart.Data(month, month2017));
                    claimsPaidByMonth2018.getData().add(new XYChart.Data(month, month2018));
                } catch (ClassCastException ex) {
                    found = false;
                    Alert alert = new Alert(AlertType.ERROR, "There was a ClassCast exception, program cannot run", ButtonType.CLOSE);
                    alert.showAndWait();
                }

            }
            yAxisMin = Math.min(returnMin(list2016), Math.min(returnMin(list2017), returnMin(list2018)));
            yAxisMax = Math.max(returnMax(list2016), Math.max(returnMax(list2017), returnMax(list2018)));
            read.close();
            fileScan.close();
        }

// use exception handling to account for a bad file. if a file is invalid,
// display an alert

        catch (FileNotFoundException e) {
            found = false;
            Alert alert = new Alert(AlertType.ERROR, "The file: " + fileName + " does not exist, cannot run the program", ButtonType.CLOSE);
            alert.showAndWait();
        } catch (IOException e) {
            found = false;
            Alert alert = new Alert(AlertType.ERROR, "There was an IO Exception running " + fileName + ", cannot run the program", ButtonType.CLOSE);
            alert.showAndWait();
        }

        return found; // this line here only so the code compiles; remove and updated as appropriate!
    }


    private int getVisuallyAppealingAxisValue(int value) {
        return (value + (TICK_INTERVAL - 1)) / TICK_INTERVAL * TICK_INTERVAL;
    }

    public static void main(String[] args) {
        launch(args);
    }

// calculate the min and max values seen and update yAxisMin and yAxisMax based
// on these values


    public int returnMax(ArrayList<Integer> list) {
        return Collections.max(list);

    }

    public int returnMin(ArrayList<Integer> list) {
        return Collections.min(list);

    }



}