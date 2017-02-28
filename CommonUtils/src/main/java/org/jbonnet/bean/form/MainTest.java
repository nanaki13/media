package org.jbonnet.bean.form;

import java.util.function.Function;

import org.jbonnet.bean.ObjectIOInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainTest extends Application {

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane root = new GridPane();
		GridPane aView = new GridPane();

		FxViewFactory.fillViewComponent(P.class, new DefaultFieldContexts(P.class), new DefaultManipulator(), aView);
		Scene scene = new Scene(root);
		root.add(aView, 0, 0, 1, 1);
		Button b = new Button("fill");
		root.add(b, 0, 1, 1, 1);
		P a = new P();
		b.setOnAction((e) -> {
			FxObjectFiller.fillObject(a,root);
			System.out.println(ObjectIOInterface.Factory.getInstance(P.class).string(a));
		});
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static class P {
		private String name;
		private String location;
		private String adresse;
		private Integer age;

		/**
		 * @return the location
		 */
		public String getLocation() {
			return location;
		}

		/**
		 * @param location the location to set
		 */
		public void setLocation(String location) {
			this.location = location;
		}

		/**
		 * @return the adresse
		 */
		public String getAdresse() {
			return adresse;
		}

		/**
		 * @param adresse the adresse to set
		 */
		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the age
		 */
		public Integer getAge() {
			return age;
		}

		/**
		 * @param age
		 *            the age to set
		 */
		public void setAge(Integer age) {
			this.age = age;
		}

	}

}
