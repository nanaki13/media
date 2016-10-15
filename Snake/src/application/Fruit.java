package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Fruit extends Group {
	public  Fruit() {
		getChildren().add(new Circle(0,0,15, Color.DARKRED));
	}
}
