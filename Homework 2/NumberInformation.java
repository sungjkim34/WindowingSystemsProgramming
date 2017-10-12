/**************************************
 
	Sungjae Kim
	Windowing Systems Programming
 	Homework 2 - NumberInformation Widget
 	
 *************************************/


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NumberInformation extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		//Visual Components
		ComboBox<String> inputOptions = new ComboBox<String>();
		inputOptions.getItems().addAll("Even", "Prime");
		inputOptions.getSelectionModel().selectFirst();
		Button runBtn = new Button("Run");
		TextArea outputField = new TextArea();
		outputField.setDisable(true);
		TextField inputField = new TextField();
		
		// Initialize Boxes
		HBox hbox = new HBox(8);
		VBox vbox = new VBox(8);
		HBox.setHgrow(inputField, Priority.ALWAYS);
		VBox.setVgrow(outputField, Priority.ALWAYS);
		hbox.getChildren().addAll(inputField, inputOptions, runBtn);
		vbox.getChildren().addAll(hbox, outputField);
		
		//Add paddings for styling
		hbox.setPadding(new Insets(10));
		vbox.setPadding(new Insets(15));

		// Event Handlers
		runBtn.setOnAction(event -> {
			String inputtedOption = inputOptions.getSelectionModel().getSelectedItem();
			if(inputtedOption == "Even"){
				try{
					int inputtedText = Integer.parseInt(inputField.getText());
					outputField.appendText(isEven(inputtedText) ? inputtedText + " is even. \n" : inputtedText + " is odd. \n");
				} catch(NumberFormatException e){
					outputField.appendText("Error - " + inputField.getText() + " is not a valid number, please enter a valid number! \n");
				}
			}
			else if(inputtedOption == "Prime"){
				try{
					int inputtedText = Integer.parseInt(inputField.getText());
					outputField.appendText(isPrime(inputtedText) ? inputtedText + " is prime. \n" : inputtedText + " is not prime. \n");
				} catch(NumberFormatException e){
					outputField.appendText("Error - " + inputField.getText() + " is not a valid number, please enter a valid number! \n");
				}
			}
			else{
				outputField.appendText("Error. Please select a valid option. \n");
			}
		});
		
		// Stage
		primaryStage.setScene(new Scene(vbox, 500, 200));
		primaryStage.show();
	}
	
	public Boolean isEven(int number){
		return number % 2 == 0 ? true : false;
	}
	
	public Boolean isPrime(int number){
		for(int i=2; i<number; i++) {
	        if(number % i == 0){
	            return false;
	        }
	    }
	    return true;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
