package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	// main timeline
	private Timeline timeline;
	private AnimationTimer timer;

	// variable for storing actual frame
	private int nbFrame = 0;
	private double dy;
	private double dx;
	private List<Fruit> frutis;
	
	private KeyCode previousKeyCode = null;
	

	@Override
	public void start(Stage stage) {
		Group p = new Group();
		Scene scene = new Scene(p);
		stage.setScene(scene);
		stage.setWidth(400);
		stage.setHeight(400);
		// p.setTranslateX(80);
		// p.setTranslateY(80);
		Snake snake = new Snake();
		scene.setOnKeyPressed((v) -> {
			KeyCode code = v.getCode();
			boolean arrow = false;
			switch (code) {
			case UP:
				dy = -2;
				dx = 0;
				arrow = true;
				break;
			case DOWN:
				dy = 2;
				dx = 0;
				arrow = true;
				break;
			case LEFT:
				dx = -2;
				dy = 0;
				arrow = true;
				break;
			case RIGHT:
				dx = 2;
				dy = 0;
				arrow = true;
				break;

			default:
				break;
			}
			

		});

		frutis = new ArrayList<>();

		p.getChildren().addAll(snake);
		stage.show();

		// create a timeline for moving the circle
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);

		// You can add a specific action when each frame is started.
		timer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				snake.move(dx, dy, nbFrame);

				nbFrame++;
				Iterator<Fruit> iterator = frutis.iterator();
				while (iterator.hasNext()) {
					Fruit next = iterator.next();
					if (next.getBoundsInParent().intersects(snake.getHead().getBoundsInParent())) {
						p.getChildren().remove(next);
						iterator.remove();
						snake.addRing();
					}

				}
				if (frutis.isEmpty()) {
					Fruit f = new Fruit();
					frutis.add(f);
					f.setTranslateX(Math.random() * 150);
					f.setTranslateY(Math.random() * 150);
					p.getChildren().add(f);
				}

			}

		};

		timeline.play();
		timer.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
