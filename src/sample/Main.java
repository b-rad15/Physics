package sample;

import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import java.awt.Toolkit;
import java.awt.Dimension;


public class Main extends Application {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();
    Stage primaryStage = new Stage();
    VBox vectorAdditionLayout = new VBox(10),
            vectorBreakdownLayout = new VBox(10);
    Label vAddMagnitude = new Label(),
            vAddDirection = new Label(),
            bdX = new Label(),
            bdY = new Label();
    TextField time = new TextField(),
            acceleration = new TextField(),
            displacement = new TextField(),
            vFinal = new TextField(),
            vInitial = new TextField();

    Scene vAddScene, vBreakScene, OneDKinematics, mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //mainScene Parts
        VBox mainLayout = new VBox(height*.05);
        mainLayout.setAlignment(Pos.CENTER);
        //Problem ChoiceBox
        ChoiceBox<String> problemChoice = new ChoiceBox<>();
        problemChoice.getItems().addAll("Vector Addition", "Vector Breakdown", "1-D Kinematics");
        problemChoice.setValue("Vector Addition");
        problemChoice.setScaleX(2);
        problemChoice.setScaleY(2);

        Label mainLabel = new Label("Select the type of problem\nthat you are doing.");
        mainLabel.setMinSize(40, 40);
        mainLabel.setStyle("-fx-font: 40 arial;");

        Button mainEnter = new Button("Enter");
        mainEnter.setScaleX(2);
        mainEnter.setScaleY(2);
        mainEnter.setOnAction(e -> primaryStage.setScene(problemPicker(problemChoice.getValue())));

        mainLayout.getChildren().add(mainLabel);
        mainLayout.getChildren().add(problemChoice);
        mainLayout.getChildren().add(mainEnter);
        Scene mainScene = new Scene(mainLayout, width*.316, height*.316);

        //vectorAddition Parts
        TextField magnitude = new TextField("Enter the magnitude of the first vector");
        TextField direction = new TextField("Enter the direction of the first vector (degrees)");
        TextField magnitude2 = new TextField("Enter the magnitude of the second vector");
        TextField direction2 = new TextField("Enter the direction of the second vector (degrees)");
        Button vAddEnter = new Button("Enter");
        vAddEnter.setOnAction(e -> addVector(Double.parseDouble(magnitude.getText()), Double.parseDouble(magnitude2.getText()), Double.parseDouble(direction.getText()), Double.parseDouble(direction2.getText())));
        Button vAddReturn = new Button("Return to\nProblem Choice");
        vAddReturn.setOnAction(e -> returnToMain());
        HBox vAddbot = new HBox();
        vAddbot.setAlignment(Pos.CENTER);
        vAddbot.getChildren().addAll(vAddEnter, vAddReturn);
        vectorAdditionLayout.getChildren().addAll(magnitude, direction, magnitude2, direction2, vAddMagnitude, vAddDirection, vAddbot);
        vAddScene = new Scene(vectorAdditionLayout,500,500);

        //vectorBreakdown Parts
        TextField magnitude3 = new TextField();
        magnitude3.setPromptText("Enter the Magnitude of the Vector");
        TextField direction3 = new TextField();
        direction3.setPromptText("Enter the direction of the vector (degrees)");
        Button bdReturn = new Button("Return to\nProblem Choice");
        bdReturn.setOnAction(e -> returnToMain());
        Button bdEnter = new Button("Enter");
        bdEnter.setOnAction(e -> bdMethod(Double.parseDouble(magnitude3.getText()), Double.parseDouble(direction3.getText())));
        HBox botbd = new HBox(.3333*.3*width);
        botbd.setAlignment(Pos.CENTER);
        botbd.getChildren().addAll(bdEnter, bdReturn);
        HBox bdAns = new HBox(.33333*.3*width);
        bdAns.setAlignment(Pos.CENTER);
        /*bdAns.setOnKeyPressed(e -> {
                                       if (e.getCode() == KeyCode.ENTER) {
                                           bdMethod(Double.parseDouble(magnitude3.getText()), Double.parseDouble(direction3.getText()));
                                       }
                    });*/
        bdAns.getChildren().addAll(bdX, bdY);
        vectorBreakdownLayout.getChildren().addAll(magnitude3, direction3, bdAns, botbd);
        vBreakScene = new Scene(vectorBreakdownLayout, width*.3, height*.5);

        //1-D Kinematics
        Label ViLabel = new Label("Initial Velocity:"),
                VfLabel = new Label("Final Velocity:"),
                aLabel = new Label("Acceleration:"),
                dLabel = new Label("Displacement:"),
                tLabel = new Label("Time:");
        Button kinReturn = new Button("Return to\nProblem Choice"),
                kinEnter = new Button("Enter");
        kinReturn.setOnAction(e -> returnToMain());
        kinEnter.setOnAction(e -> kinChoice(vInitial.getText(), vFinal.getText(), acceleration.getText(), displacement.getText(), time.getText()));
        VBox OneDKinematicsLayout = new VBox(ViLabel, vInitial, VfLabel, vFinal,  aLabel, acceleration,  dLabel, displacement,  tLabel, time,  kinEnter);
        OneDKinematics = new Scene(OneDKinematicsLayout, width*.3, height*.5);

        primaryStage.setTitle("Physics");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private double vectorAdditionMag(double mag1, double mag2, double dir1, double dir2){
        return Math.sqrt(Math.pow(mag1 * Math.cos(dir1)+ mag2 * Math.cos(dir2),2) + Math.pow(mag1 * Math.sin(dir1) + mag2 * Math.sin(dir2), 2));
    }

    private double vectorAdditionDir(double mag1, double mag2, double dir1, double dir2){
        double dir = Math.atan((mag1 * Math.sin(dir1) + mag2 * Math.sin(dir2)) / (mag1 * Math.cos(dir1)+ mag2 * Math.cos(dir2)));
        if(dir < 0 ){
            dir += 2*Math.PI;
        }
        return dir;
    }

    private double vectorBreakdownX(double mag, double dir){
        return mag*Math.cos(dir);
    }

    private double vectorBreakdownY(double mag, double dir){
        return mag*Math.sin(dir);
    }

    private void bdMethod(double mag, double dir){
        bdX.setText("i-hat = " + vectorBreakdownX(mag, Math.toRadians(dir)));
        bdY.setText("j-hat = "+ vectorBreakdownY(mag, Math.toRadians(dir)));
    }
    private void addVector(double mag1, double mag2, double dir1, double dir2){
        vAddMagnitude.setText("Magnitude:\t" + Double.toString((vectorAdditionMag(mag1, mag2, dir1, dir2))));
        vAddDirection.setText("Direction:\t\t" + Double.toString(Math.toDegrees(vectorAdditionDir(mag1, mag2, Math.toRadians(dir1), Math.toRadians(dir2)))) + "°");
    }

    private void returnToMain(){
        primaryStage.setScene(mainScene);
    }

    private Button returnButton(){
        Button name = new Button("Return to\nProblem Choice");
        name.setOnAction(e -> primaryStage.setScene(mainScene));
        return name;
    }

    private void kinChoice(String vi, String vf, String a, String d, String t){
        //System.out.println(vi+"\n"+vf+"\n"+a+"\n"+d+"\n"+t);
        if(vf.equals("")){
            if(vi.equals("")){
                 //vInitial.setText(Double.toString(vinovf(Double.parseDouble(a), Double.parseDouble(d), Double.parseDouble(t))));
                 vFinal.setText(Double.toString(vfnovi(Double.parseDouble(a), Double.parseDouble(d), Double.parseDouble(t))));
            } else if(a.equals("")){
                vFinal.setText(Double.toString(vfnoa(Double.parseDouble(vi), Double.parseDouble(d), Double.parseDouble(t))));
            } else if(d.equals("")){
                vFinal.setText(Double.toString(vfnod(Double.parseDouble(vi), Double.parseDouble(a), Double.parseDouble(t))));
            } else {
                vFinal.setText(Double.toString(vfnot(Double.parseDouble(vi), Double.parseDouble(a), Double.parseDouble(d))));
            }
        } else if(vi.equals("")){
            if(vf.equals("")){
                //vFinal.setText(Double.toString(vinovf(Double.parseDouble(a), Double.parseDouble(d), Double.parseDouble(t))));
                vInitial.setText(Double.toString(vfnovi(Double.parseDouble(a), Double.parseDouble(d), Double.parseDouble(t))));
            } else if(a.equals("")){
                vInitial.setText(Double.toString(vfnoa(Double.parseDouble(vi), Double.parseDouble(d), Double.parseDouble(t))));
            } else if(d.equals("")){
                vInitial.setText(Double.toString(vfnod(Double.parseDouble(vi), Double.parseDouble(a), Double.parseDouble(t))));
            } else {
                vInitial.setText(Double.toString(vfnot(Double.parseDouble(vi), Double.parseDouble(a), Double.parseDouble(d))));
            }
        } else if(a.equals("")){
            if(vi.equals("")){
                //vInitial.setText(Double.toString(vinovf(Double.parseDouble(a), Double.parseDouble(d), Double.parseDouble(t))));
                acceleration.setText(Double.toString(vfnovi(Double.parseDouble(a), Double.parseDouble(d), Double.parseDouble(t))));
            } else if(vf.equals("")){
                acceleration.setText(Double.toString(vfnoa(Double.parseDouble(vi), Double.parseDouble(d), Double.parseDouble(t))));
            } else if(d.equals("")){
                acceleration.setText(Double.toString(vfnod(Double.parseDouble(vi), Double.parseDouble(a), Double.parseDouble(t))));
            } else {
                acceleration.setText(Double.toString(vfnot(Double.parseDouble(vi), Double.parseDouble(a), Double.parseDouble(d))));
            }
        } else if(d.equals("")){
            if(vi.equals("")){
                //vInitial.setText(Double.toString(vinovf(Double.parseDouble(a), Double.parseDouble(d), Double.parseDouble(t))));
                displacement.setText(Double.toString(vfnovi(Double.parseDouble(a), Double.parseDouble(d), Double.parseDouble(t))));
            } else if(vf.equals("")){
                displacement.setText(Double.toString(vfnoa(Double.parseDouble(vi), Double.parseDouble(d), Double.parseDouble(t))));
            } else if(a.equals("")){
                displacement.setText(Double.toString(vfnod(Double.parseDouble(vi), Double.parseDouble(a), Double.parseDouble(t))));
            } else {
                displacement.setText(Double.toString(vfnot(Double.parseDouble(vi), Double.parseDouble(a), Double.parseDouble(d))));
            }
        } else {
            if(vi.equals("")){
                //vInitial.setText(Double.toString(vinovf(Double.parseDouble(a), Double.parseDouble(d), Double.parseDouble(t))));
                time.setText(Double.toString(vfnovi(Double.parseDouble(a), Double.parseDouble(d), Double.parseDouble(t))));
            } else if(vf.equals("")){
                time.setText(Double.toString(vfnoa(Double.parseDouble(vi), Double.parseDouble(d), Double.parseDouble(t))));
            } else if(a.equals("")){
                time.setText(Double.toString(vfnod(Double.parseDouble(vi), Double.parseDouble(a), Double.parseDouble(t))));
            } else {
                time.setText(Double.toString(vfnot(Double.parseDouble(vi), Double.parseDouble(a), Double.parseDouble(d))));
            }
        }
         

        //System.out.println(vi+"\n"+vf+"\n"+a+"\n"+d+"\n"+t);
    }

    private double vfnod(double vi, double a, double t){
        return vi + a*t;
    }

    private double vfnot(double vi, double a, double d){
        return Math.sqrt(Math.pow(vi, 2) + 2*a*d);
    }

    private double vfnoa(double vi, double d, double t){
        return (2*d/t) - vi;
    }

    private double vfnovi(double a, double d, double t){
        return (d + .5*a*t)/2;
    }
/*

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }

    private double no(double vi, double vf, double a, double d, double t){

    }
*/


    private Scene problemPicker(String choice){
        switch(choice){
            case "Vector Addition":
                return vAddScene;
            case "Vector Breakdown":
                return vBreakScene;
            case "1-D Kinematics":
                return OneDKinematics;
            default:
                return mainScene;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
