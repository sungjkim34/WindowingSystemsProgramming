/**************************************
 
	Sungjae Kim
	Windowing Systems Programming
 	Homework 3 - Main Transcode Widget Interface
 	
 *************************************/


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainInterface extends Application {

	@Override
	public void start(Stage primaryStage) {

		// Pane & Boxes
		TabPane tabPane = new TabPane();
		ScrollPane scrollPane = new ScrollPane();
		
		//Visual Components
		TranscodeWidget transcodeWidget = new TranscodeWidget();
		Button transcodeButton = new Button("Add Transcode");
		Tab tab1 = new Tab();
		tab1.setText("Home");
		Tab tab2 = new Tab();
		tab2.setText("Transcoder");
		tab2.setContent(scrollPane);
		tabPane.getTabs().add(tab1);
		scrollPane.setContent(transcodeWidget.open(primaryStage));
		scrollPane.setFitToWidth(true);
		Region region = new Region();
		
		//Boxes
		HBox row1 = new HBox();
		HBox row2 = new HBox();
		VBox vbox = new VBox();
		row1.getChildren().add(tabPane);
		HBox.setHgrow(tabPane, Priority.ALWAYS);
		HBox.setHgrow(region, Priority.ALWAYS);
		VBox.setVgrow(row1, Priority.ALWAYS);
		row2.getChildren().addAll(region, transcodeButton);
		vbox.getChildren().addAll(row1, row2);
		
		// Event Handlers
		transcodeButton.setOnAction(event -> {
			tabPane.getTabs().add(tab2);
		});
		
		// Stage
		primaryStage.setScene(new Scene(vbox, 500, 200));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
