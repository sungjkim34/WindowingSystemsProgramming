/**************************************
 
	Sungjae Kim
	Windowing Systems Programming
 	Homework 1
 	
 *************************************/


import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Pane & Boxes
		GridPane leftPane = new GridPane();
		GridPane rightPane = new GridPane();
		HBox hbox = new HBox();
		// Buttons
		Button submitBtn = new Button("Submit");
		rightPane.add(submitBtn, 3, 0);
		Button printBtn = new Button("Print");
		rightPane.add(printBtn, 3, 1);
		// Region
		Region region = new Region();
		HBox.setHgrow(region, Priority.ALWAYS);
		// Checkboxes
		CheckBox checkbox1 = new CheckBox("Fruit");
		leftPane.add(checkbox1, 0, 0);
		CheckBox checkbox2 = new CheckBox("Cars");
		leftPane.add(checkbox2, 1, 0);
		// Radio Group 1
		ToggleGroup tg1 = new ToggleGroup();
		RadioButton rb_g1_1 = new RadioButton("Apples");
		rb_g1_1.setUserData("Apples");
		rb_g1_1.setToggleGroup(tg1);
		rb_g1_1.disableProperty().bind(checkbox1.selectedProperty().not());
		leftPane.add(rb_g1_1, 0, 1);
		RadioButton rb_g1_2 = new RadioButton("Bananas");
		rb_g1_2.setUserData("Bananas");
		rb_g1_2.setToggleGroup(tg1);
		rb_g1_2.disableProperty().bind(checkbox1.selectedProperty().not());
		leftPane.add(rb_g1_2, 0, 2);
		RadioButton rb_g1_3 = new RadioButton("Oranges");
		rb_g1_3.setUserData("Oranges");
		rb_g1_3.setToggleGroup(tg1);
		rb_g1_3.disableProperty().bind(checkbox1.selectedProperty().not());
		leftPane.add(rb_g1_3, 0, 3);
		// Radio Group 2
		ToggleGroup tg2 = new ToggleGroup();
		RadioButton rb_g2_1 = new RadioButton("Audi");
		rb_g2_1.setUserData("Audi");
		rb_g2_1.setToggleGroup(tg2);
		rb_g2_1.disableProperty().bind(checkbox2.selectedProperty().not());
		leftPane.add(rb_g2_1, 1, 1);
		RadioButton rb_g2_2 = new RadioButton("BMW");
		rb_g2_2.setUserData("BMW");
		rb_g2_2.setToggleGroup(tg2);
		rb_g2_2.disableProperty().bind(checkbox2.selectedProperty().not());
		leftPane.add(rb_g2_2, 1, 2);
		RadioButton rb_g2_3 = new RadioButton("Mercedes");
		rb_g2_3.setUserData("Mercedes");
		rb_g2_3.setToggleGroup(tg2);
		rb_g2_3.disableProperty().bind(checkbox2.selectedProperty().not());
		leftPane.add(rb_g2_3, 1, 3);
		// Stage
		hbox.getChildren().addAll(leftPane, region, rightPane);
		primaryStage.setScene(new Scene(hbox, 500, 200));
		primaryStage.show();

		ArrayList<String> submitHistory = new ArrayList<String>();
		// Event Handlers
		submitBtn.setOnAction(event -> {
			if (checkbox1.isSelected() && !checkbox2.isSelected() && tg1.getSelectedToggle() != null) {
				submitHistory.add(tg1.getSelectedToggle().getUserData().toString());
			}
			if (checkbox2.isSelected() && !checkbox1.isSelected() && tg2.getSelectedToggle() != null) {
				submitHistory.add(tg2.getSelectedToggle().getUserData().toString());
			}
			if (checkbox1.isSelected() && checkbox2.isSelected() && tg1.getSelectedToggle() != null
					&& tg2.getSelectedToggle() != null) {
				submitHistory.add(tg1.getSelectedToggle().getUserData().toString() + " "
						+ tg2.getSelectedToggle().getUserData().toString());
			}
		});
		printBtn.setOnAction(event -> {
			System.out.println("----------------------");
			for (String submit : submitHistory) {
				System.out.println(submit);
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
