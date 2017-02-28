package org.jbonnet;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainFish extends Application {

	private static final int SIZE_EAU_X = 28;
	private static final int SIZE_EAU_Y = 28;
	private static final int SIZE_X = SIZE_EAU_X + 2;
	private static final int SIZE_Y = SIZE_EAU_Y + 2;
	private static final int SIZE_CASE_X = 20;
	private static final int SIZE_CASE_Y = 20;
	private static final int MARGE = 1;
	private static final Image FOND1 = getImage("/img/fond1.png", SIZE_CASE_X - MARGE, SIZE_CASE_Y - MARGE);
	private static final Image FOND2 = getImage("/img/fond2.png", SIZE_CASE_X - MARGE, SIZE_CASE_Y - MARGE);;
	private static final Image FOND3 = getImage("/img/fond3.png", SIZE_CASE_X - MARGE, SIZE_CASE_Y - MARGE);;
	private LinkedList<Double> last3Delta = new LinkedList<>();

	public static void main(String[] args) {
		Application.launch(args);

	}

	private Random random = new Random();
	private Plateau<Case<ImageView>> plateau;
	private Double prviousY;
	private Group root;
	private boolean control;
	private Double prviousX;

	@Override
	public void start(Stage primaryStage) {
		plateau = new Plateau<>(SIZE_X, SIZE_Y, Case::new);
		root = new Group();
		Duration time;
		System.out.println(Fish.FISHS);
		KeyFrame ke = new KeyFrame(new Duration(1000), (e) -> {
			plateau.iterator().forEachRemaining((c) -> changeImage(c));
		});
		Timeline timeline = new Timeline(ke);
		timeline.setCycleCount(Timeline.INDEFINITE);
		Scene scene = new Scene(root, Color.BLACK);
		// root.prefHeight(500);
		// root.prefWidth(500);

		for (int i = 0; i < SIZE_X; i++) {
			for (int j = 0; j < SIZE_Y; j++) {

				// Rectangle p = new Rectangle();

				// p.setHeight(SIZE_CASE_X - MARGE);
				// p.setWidth(SIZE_CASE_Y - MARGE);
				Image c = getImage(i, j);
				ImageView p = new ImageView(c);
				p.setY(i * SIZE_CASE_X);
				p.setX(j * SIZE_CASE_Y);
				Case<ImageView> case1 = plateau.getCase(i, j);
				case1.setObject(p);
				case1.setX(i);
				case1.setY(j);

				// p.setFill(c);
				root.getChildren().add(p);

				// Case c = new Case();
			}
		}
		scene.setOnMouseDragged((s) -> drag(s));
		scene.setOnMouseDragExited((s) -> dragExit(s));

		scene.setOnKeyPressed((k) -> keyPressed(k));
		scene.setOnKeyReleased((k) -> keyReleased(k));
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);

		primaryStage.show();
		timeline.play();

	}

	private void changeImage(Case<ImageView> c) {
		int nextInt = random.nextInt(10);
		if(nextInt == 0)
			c.getObject().setImage(getImage(c.getX(), c.getY()));

	}

	private Image getImage(int i, int j) {
		if (i == 0 || i == SIZE_X - 1 || j == 0 || j == SIZE_Y - 1) {
			return FOND1;
		}
		int nx = i - SIZE_X / 2;
		int ny = j - SIZE_Y / 2;
		double rMax = Math.sqrt(SIZE_EAU_X * SIZE_EAU_X / 4 + SIZE_EAU_Y * SIZE_EAU_Y / 4);
		double r = Math.sqrt(nx * nx + ny * ny);
		// System.out.println("i : "+i);
		// System.out.println("j : "+j);
		// System.out.println(rMax);
		// System.out.println(r );
		double p3;
		double p2;
		double p1;
		if (r > rMax * 0.66) {
			p1 = 8d / 10d;
			p2 = 2d / 10d;
			p3 = 0;
		} else if (r > rMax * 0.33) {
			p1 = 1d / 10d;
			p2 = 8d / 10d;
			p3 = 1d / 10d;
		} else {
			p1 = 0d / 10d;
			p2 = 2d / 10d;
			p3 = 8d / 10d;
		}
		double random2 = Math.random();
		// System.out.println("p1 : "+p1);
		// System.out.println("p2 : "+p2);
		// System.out.println("p3 : "+p3);
		if (random2 < p1) {
			return FOND1;
		} else if (random2 < p1 + p2) {
			return FOND2;
		}
		return FOND3;

	}

	private static Image getImage(String string, double i, double j) {
		try (InputStream is = MainFish.class.getResourceAsStream(string);) {
			return new Image(is, i, j, false, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void keyPressed(KeyEvent k) {
		control = (k.getCode() == KeyCode.CONTROL);
	}

	private void keyReleased(KeyEvent k) {
		control = !(k.getCode() == KeyCode.CONTROL);
	}

	private void dragExit(MouseDragEvent s) {
		prviousY = null;
		prviousX = null;
	}

	private void drag(MouseEvent s) {

		if (control) {
			zoom(s);
		} else {
			move(s);
		}

	}

	private void move(MouseEvent s) {
		if (prviousY == null) {
			prviousY = s.getScreenY();
		}
		if (prviousX == null) {
			prviousX = s.getScreenX();
		} else {

			double dy = s.getScreenY() - prviousY;
			prviousY = s.getScreenY();
			if (Math.abs(dy) < 30) {

				root.setTranslateY(root.getTranslateY() + dy);
			}

			//
			double dx = s.getScreenX() - prviousX;
			prviousX = s.getScreenX();
			if (Math.abs(dx) < 30) {

				root.setTranslateX(root.getTranslateX() + dx);
			}

		}

	}

	private void zoom(MouseEvent s) {
		if (prviousY == null) {
			prviousY = s.getScreenY();
		} else {
			double dy = s.getScreenY() - prviousY;
			prviousY = s.getScreenY();
			if (last3Delta.size() > 3) {
				last3Delta.removeFirst();
			}
			last3Delta.add(dy);
			if (dy > 0) {
				root.setScaleX(root.getScaleX() * 1.05);
				root.setScaleY(root.getScaleY() * 1.05);
			} else if (dy < 0) {
				root.setScaleX(root.getScaleX() * 0.95);
				root.setScaleY(root.getScaleY() * 0.95);
			}

		}

	}

	private Paint getColor(int i, int j) {
		if (i == 0 || i == SIZE_X - 1 || j == 0 || j == SIZE_Y - 1) {
			return Color.BROWN;
		}
		int nx = i - SIZE_X / 2;
		int ny = j - SIZE_Y / 2;
		double rMax = Math.sqrt(SIZE_EAU_X * SIZE_EAU_X / 4 + SIZE_EAU_Y * SIZE_EAU_Y / 4);
		double r = Math.sqrt(nx * nx + ny * ny);
		// System.out.println("i : "+i);
		// System.out.println("j : "+j);
		// System.out.println(rMax);
		// System.out.println(r );
		double p3;
		double p2;
		double p1;
		if (r > rMax * 0.66) {
			p1 = 8d / 10d;
			p2 = 2d / 10d;
			p3 = 0;
		} else if (r > rMax * 0.33) {
			p1 = 1d / 10d;
			p2 = 8d / 10d;
			p3 = 1d / 10d;
		} else {
			p1 = 0d / 10d;
			p2 = 2d / 10d;
			p3 = 8d / 10d;
		}
		double random2 = Math.random();
		// System.out.println("p1 : "+p1);
		// System.out.println("p2 : "+p2);
		// System.out.println("p3 : "+p3);
		if (random2 < p1) {
			return Color.AQUA;
		} else if (random2 < p1 + p2) {
			return Color.AQUAMARINE;
		}
		return Color.MEDIUMAQUAMARINE;

	}

}
