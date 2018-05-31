import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class MainGUI extends Application {

	Stage primaryStage;
    Button choose;
    Scene main, SingVector, addVector, Kinematics1, Kinematics2; 
    Label label1, 
    VxLabel = new Label(), 
    VyLabel = new Label();
    VBox Kinematics1layout, Kinematics2layout, SingVectorLayout, layout1;
    TextField time, displacement, acceleration, initialVelocity, finalVelocity;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	Label label1 = new Label("Choose what type of problem you are doing.");
        primaryStage.setTitle("Physics");
        VBox layout1 = new VBox(30);
        Button choose = new Button("Choose");
        ChoiceBox<String> equationChoice = new ChoiceBox<>();
        Button button2 = new Button("Go back to main");
        TextField magnitude = new TextField();
        TextField direction = new TextField();
        Button SingVectorEnter = new Button("Enter");
        VBox SingVectorLayout = new VBox(10);
        VBox Kinematics1layout = new VBox(10);
        VBox Kinematics2layout = new VBox(10);
        ChoiceBox<String> unusedChoice = new ChoiceBox<>();
        Button unusedPick = new Button("Enter");
        TextField time = new TextField();
        TextField displacement = new TextField();
        TextField finalVelocity = new TextField();
        TextField initialVelocity = new TextField();
        TextField acceleration = new TextField();
       //unusedChoice.getItems().addAll("Time", "Acceleration", "Displacement", "Final Velocity", "Initial Velocity");
        equationChoice.getItems().addAll("Vector Addition", "Single Vector Calculations", "Time", "Acceleration", "Displacement", "Final Velocity", "Initial Velocity");
        magnitude.setPromptText("Enter the magnitude of the vector");        
        direction.setPromptText("Enter the direction of the vector in degrees");        
        SingVectorLayout.getChildren().addAll(magnitude, direction, SingVectorEnter, button2, VxLabel, VyLabel) ;
        SingVector = new Scene(SingVectorLayout, 480, 700);        
        Kinematics1layout.getChildren().addAll(unusedChoice, unusedPick);
        layout1.getChildren().addAll(label1, equationChoice, choose);
        main = new Scene(layout1, 300, 300);
        primaryStage.setScene(main);
        Kinematics1 = new Scene(Kinematics1layout, 480, 700);
        
        choose.setOnAction(e -> primaryStage.setScene(choiceBoxSwitch(equationChoice)));
        SingVectorEnter.setOnAction(e -> equationVx(magnitude.getText(), direction.getText()));
        //SingVectorEnter.setOnAction(e -> equationVy(magnitude.getText(), direction.getText()));
        button2.setOnAction(e -> primaryStage.setScene(main));
        unusedPick.setOnAction(e -> kinematicsMethod(unusedChoice.getValue()));
        primaryStage.setTitle("Physics");
        primaryStage.show();
        
    }
   private void kinematicsMethod(String choice){
	   switch(choice){
	   case "Time":
	        Kinematics2layout.getChildren().addAll(displacement, finalVelocity, initialVelocity, acceleration);
		   break;
	   case "Acceleration":
	        Kinematics2layout.getChildren().addAll(displacement, finalVelocity, initialVelocity, time);
		   break;
	   case "Displacement":
	        Kinematics2layout.getChildren().addAll(finalVelocity, initialVelocity, acceleration, time);
		   break;
	   case "Final Velocity":
	        Kinematics2layout.getChildren().addAll(displacement, initialVelocity, acceleration, time);	   
		   break;
	   case "Initial Velocity":
	        Kinematics2layout.getChildren().addAll(displacement, finalVelocity, acceleration, time);
		   break;
	   default:
		   break;
	   }
	   Kinematics2 = new Scene(Kinematics2layout, 480, 700);
    	primaryStage.setScene(Kinematics2);
    	
    }
	private void equationVx(String mag, String dir){
		float Vm = Float.parseFloat(mag);
		float Vd = (float) Math.toRadians(Float.parseFloat(dir));
		float Vx = (float) (Vm * Math.cos(Vd));
		if (Vx <= .000009 && Vx >= -.000009){
			Vx = 0;
		}
		VxLabel.setText(Float.toString(Vx));
		equationVy(mag, dir);
	}
	private void equationVy(String mag, String dir){
		float Vm = Float.parseFloat(mag);
		float Vd = (float) Math.toRadians(Float.parseFloat(dir));
		//float Vx = (float) (Vm * Math.cos(Vd));
		float Vy = (float) (Vm * Math.sin(Vd));
		if (Vy <= .000009 && Vy >= -.000009){
			Vy = 0;
		}
		VyLabel.setText(Float.toString(Vy));
	}
    private Scene choiceBoxSwitch(ChoiceBox<String> choiceBox){
    	String choice = choiceBox.getValue();
    	switch (choice){
    		case "Vector Addition": return addVector;
    		case "Single Vector Calculations": return SingVector;
    		case "Time":
    		case "Acceleration":
    		case "Displacement":
    		case "Final Velocity":
    		case "Initial Velocity":return Kinematics1;
    		default: return main;
    	}

    }
}


