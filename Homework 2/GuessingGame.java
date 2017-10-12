/**************************************
 
	Sungjae Kim
	Windowing Systems Programming
 	Homework 2 - GuessingGame Widget
 	
 *************************************/


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class GuessingGame extends Application {
	
	private int correctGuesses = 0;
	private int incorrectGuesses = 0;
	@Override
	public void start(Stage primaryStage) {
		
		// Pane & Boxes
		GridPane leftPane = new GridPane();
		GridPane rightPane = new GridPane();
		
		//Visual Components
		Button guessBtn = new Button("Guess");
		rightPane.add(guessBtn, 0, 0);
		TextField scoreField = new TextField();
		scoreField.setDisable(true);
		rightPane.add(scoreField, 0, 1);
		ToggleGroup tg1 = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Option 1");
		rb1.setUserData("Option 1");
		rb1.setToggleGroup(tg1);
		leftPane.add(rb1, 0, 0);
		RadioButton rb2 = new RadioButton("Option 2");
		rb2.setUserData("Option 2");
		rb2.setToggleGroup(tg1);
		leftPane.add(rb2, 0, 1);
		RadioButton rb3 = new RadioButton("Option 3");
		rb3.setUserData("Option 3");
		rb3.setToggleGroup(tg1);
		leftPane.add(rb3, 1, 0);
		RadioButton rb4 = new RadioButton("Option 4");
		rb4.setUserData("Option 4");
		rb4.setToggleGroup(tg1);
		leftPane.add(rb4, 1, 1);
		
		// Region
		Region region = new Region();
		HBox.setHgrow(region, Priority.ALWAYS);
		
		//Boxes
		HBox hbox = new HBox();
		HBox.setHgrow(region, Priority.ALWAYS);
		hbox.getChildren().addAll(leftPane, region, rightPane);
		
		//Add paddings for styling
		hbox.setPadding(new Insets(10));

		// Event Handlers
		guessBtn.setOnAction(event -> {
			double randomNumber = Math.random();
			if(randomNumber < 0.25){
				correctGuesses++;
			}
			else{
				incorrectGuesses++;
			}
			scoreField.setText("Correct: " + correctGuesses + " Incorrect: " + incorrectGuesses);
		});
		
		// Stage
		primaryStage.setScene(new Scene(hbox, 500, 200));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
