/**************************************
 
	Sungjae Kim
	Windowing Systems Programming
 	Homework 3 - Transcode Widget
 	
 *************************************/


import java.io.File;
import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TranscodeWidget{
	
	private String sourceFile = "";
	private String targetFile = "";
	private double quality;
	
	public VBox open(Stage primaryStage){
		//Visual Components
		TextField sourceTextField = new TextField();
		sourceTextField.setDisable(true);
		Button sourceButton = new Button("Get Source");
		
		TextField targetTextField = new TextField();
		targetTextField.setDisable(true);
		Button targetButton = new Button("Get Target");
		
		Slider qualitySlider = new Slider();
		qualitySlider.setMin(1);
		qualitySlider.setMax(9);
		
		Button startButton = new Button("Start");
		
		ProgressBar progressBar = new ProgressBar(0.0);
		Button stepButton = new Button("Step");
		
		FileChooser fileChooser = new FileChooser();
		
		// Initialize Boxes
		HBox row1 = new HBox(8);
		HBox row2 = new HBox(8);
		HBox row3 = new HBox(8);
		HBox row4 = new HBox(8);
		HBox row5 = new HBox(8);
		VBox vbox = new VBox(8);
		HBox.setHgrow(sourceTextField, Priority.ALWAYS);
		HBox.setHgrow(targetTextField, Priority.ALWAYS);
		HBox.setHgrow(qualitySlider, Priority.ALWAYS);
		row1.getChildren().addAll(sourceTextField, sourceButton);
		row2.getChildren().addAll(targetTextField, targetButton);
		row3.getChildren().addAll(qualitySlider);
		row4.getChildren().addAll(startButton);
		row5.getChildren().addAll(progressBar, stepButton);
		vbox.getChildren().addAll(row1, row2, row3, row4, row5);
		progressBar.prefWidthProperty().bind(vbox.widthProperty().subtract(80));
		
		//Add paddings for styling
		vbox.setPadding(new Insets(15));
		
		// Event Handlers
		sourceButton.setOnAction(event -> {
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			if(selectedFile != null){
				sourceTextField.setText(selectedFile.toString());
			}
		});
		
		targetButton.setOnAction(event -> {
			File selectedFile = fileChooser.showSaveDialog(primaryStage);
			if(selectedFile != null){
				targetTextField.setText(selectedFile.toString());
			}
		});
		
		startButton.setOnAction(event -> {
			if(sourceTextField.getText().isEmpty() || targetTextField.getText().isEmpty()){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Source or target text field is empty.");
				alert.showAndWait();
			}
			sourceFile = sourceTextField.getText();
			targetFile = targetTextField.getText();
			quality = qualitySlider.getValue();
			progressBar.setProgress(0.0);
		});
		
		stepButton.setOnAction(event -> {
			DecimalFormat df = new DecimalFormat("#.#");
			double progress = Double.parseDouble(df.format(progressBar.getProgress()));
			if(sourceFile.isEmpty() || targetFile.isEmpty()){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Valid settings needs to be stored.");
				alert.showAndWait();
			}
			else if(progress < 1 && !progressBar.isIndeterminate()){
				progressBar.setProgress(progress + 0.1);
			}
			else if(progress == 1 && !progressBar.isIndeterminate()){
				progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("Complete");
				alert.setContentText("Encoding is complete.");
				alert.showAndWait();
			}
		});
		return vbox;
	}
}
