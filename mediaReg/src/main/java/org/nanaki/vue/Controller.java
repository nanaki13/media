package org.nanaki.vue;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Controller extends Application {

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane borderPane = new BorderPane();
		Menu menu = new Menu();
		MenuBar menuBar = new MenuBar();
		 menuBar.getMenus().add(menu1);
		Scene scene = new Scene(borderPane);
		primaryStage.setScene(scene);

	}

}
