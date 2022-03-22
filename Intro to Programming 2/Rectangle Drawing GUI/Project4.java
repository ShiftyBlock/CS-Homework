import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Project4 extends Application {

    private Rectangle rect;
    private Rectangle border;
    private boolean lineBeingDrawn;
    private RadioButton red, blue, yellow;
    private RadioButton thin, thick, fill;
    private Button clear= new Button();
    public void start(Stage primaryStage) {

        lineBeingDrawn = false;
        Pane pane = new Pane();
        VBox main= new VBox();
        main.setAlignment(Pos.TOP_CENTER);
        main.setSpacing(10);
        Text col= new Text("Choose Color");
        ToggleGroup group= new ToggleGroup();
        blue= new RadioButton("Blue Color");
        red= new RadioButton("Red Color");
        yellow= new RadioButton("Yellow Color");
        blue.setSelected(true);
        blue.setToggleGroup(group);
        red.setToggleGroup(group);
        yellow.setToggleGroup(group);
        clear.setOnAction(this::handleButton);
        ToggleGroup fillGroup= new ToggleGroup();
        Text br= new Text("Choose Brush");
        thin= new RadioButton("Thin");
        thick= new RadioButton("Thick");
        fill= new RadioButton("Fill");
        thin.setSelected(true);
        thin.setToggleGroup(fillGroup);
        thick.setToggleGroup(fillGroup);
        fill.setToggleGroup(fillGroup);
        VBox color= new VBox(col, blue, red, yellow);
        VBox brush= new VBox(br,thin, thick , fill);
        Text cl= new Text("CLEAR");
        VBox clearBox= new VBox(cl, clear);
        clearBox.setSpacing(10); clearBox.setAlignment(Pos.BOTTOM_LEFT);
        color.setSpacing(10); color.setAlignment(Pos.TOP_CENTER);
        brush.setSpacing(10); brush.setAlignment(Pos.TOP_CENTER);
        main.getChildren().add(color);
        main.getChildren().add(brush);
        main.getChildren().add(clearBox);
        //pane.setOnMouseClicked();
        pane.setOnMouseClicked(this::handleMouseClicks);
        pane.setOnMouseMoved(this::handleMouseMotion);

        rect = new Rectangle(150, 150, 10, 10);
        border= new Rectangle(150, 150, 5, 5);
        border.setFill(Color.WHITE);
        rect.setFill(Color.BLUE);
        pane.getChildren().add(rect);
        pane.getChildren().add(border);
        pane.getChildren().add(main);


        Scene scene = new Scene(pane, 500, 500, Color.WHITE);
        primaryStage.setTitle("Project 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButton(javafx.event.ActionEvent actionEvent) {
        rect.setVisible(false);
        border.setVisible(false);
    }


    private void handleMouseClicks(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        rect.setVisible(true);
        if (blue.isSelected()){
            rect.setFill(Color.BLUE);
        }
        if (red.isSelected()){
            rect.setFill(Color.RED);
        }
        if (yellow.isSelected()){
            rect.setFill(Color.YELLOW);
        }

        if(!lineBeingDrawn) { // lineBeingDrawn==false
            lineBeingDrawn = true;
            if (thin.isSelected()){
                border.setX(x-2);
                border.setY(y-2);
            }
            if (thick.isSelected()){
                border.setX(x-5);
                border.setY(y-5);
            }
            if (fill.isSelected()){
                border.setVisible(false);
            }
            if (!fill.isSelected()){
                border.setVisible(true);
            }
            rect.setX(x);
            rect.setY(y);

        } else { // lineBeingDrawn==true
            lineBeingDrawn = false;

        }
    }
    private void handleMouseMotion(MouseEvent event) {
        if(lineBeingDrawn) {
            double x = event.getX();
            double y = event.getY();
            double rx= rect.getX();
            double ry= rect.getY();
            rect.setWidth(x-rx);
            rect.setHeight(y-ry);
            border.setWidth(x-rx-2);
            border.setHeight(y-ry-2);



            System.out.println(event.getX()+" "+event.getY());
        }

    }

    public static void main(String[] args) {
        launch(args);

    }

}
