package org.jbonnet;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;

import org.jbonnet.Constants.Deep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

@SpringBootApplication
public class MainFish extends AbstractJavaFxApplicationSupport {


	
	private static final int SIZE_CASE_X = 60;
	private static final int SIZE_CASE_Y = 60;
	private static final int MARGE = 3;
	private static final Image FOND1 = getImage("/img/fond1.gif", SIZE_CASE_X - MARGE, SIZE_CASE_Y - MARGE);
	private static final Image FOND2 = getImage("/img/fond2.gif", SIZE_CASE_X - MARGE, SIZE_CASE_Y - MARGE);
	private static final Image FOND3 = getImage("/img/fond3.gif", SIZE_CASE_X - MARGE, SIZE_CASE_Y - MARGE);
	private static final Image FOND4 = getImage("/img/fond4.gif", SIZE_CASE_X - MARGE, SIZE_CASE_Y - MARGE);
	private static final Image[] images = {FOND1,FOND2,FOND3,FOND4};
	private LinkedList<Double> last3Delta = new LinkedList<>();

	public static void main(String[] args) {
//		Application.launch(args);
		launchApp(MainFish.class, args);

	}

	private Random random = new Random();
	private Double prviousY;
	private Group root;
	private boolean control;
	private Double prviousX;
	
	@Autowired
	FishEngine fishEngine;
	private Group background;
	private Group labels;

	@Override
	public void start(Stage primaryStage) {
		
		
		root = new Group();
		background = new Group();
		labels = new Group();
		root.getChildren().addAll(background,labels);
		Duration time;
		Label label = new Label("Voilà le début ...\nDrag souris pour bouger.\nCTRL drag souris pour zoomer.\nBref un jeux de dingue.\nCliquer moi dessus pour me jarter.");
		label.setFont(new Font(20));
		label.setBackground(new Background(new BackgroundFill(Color.WHITE,  new CornerRadii(10),new Insets(0))));
		label.setLayoutX(14*60);
		label.setLayoutY(14*60);
		label.setOnMouseClicked((e)->root.getChildren().remove(label));
//		KeyFrame ke = new KeyFrame(new Duration(1000), (e) -> {
//			plateau.iterator().forEachRemaining((c) -> changeImage(c));
//		});
//		Timeline timeline = new Timeline(ke);
//		timeline.setCycleCount(Timeline.INDEFINITE);
		Scene scene = new Scene(root, Color.BLACK);
		// root.prefHeight(500);
		// root.prefWidth(500);

		for (int i = 0; i < fishEngine.getSizeX(); i++) {
			for (int j = 0; j < fishEngine.getSizeY(); j++) {


				
				Plateau<Case<CaseContent, ImageView>> pl = fishEngine.getPlateau();
				Case<CaseContent,ImageView> case1 =  pl.getCase(i, j);
				
				Image c = getRandomImage(case1.getModel().getDeep());
				ImageView p = new ImageView(c);
				p.setY(i * SIZE_CASE_X);
				p.setX(j * SIZE_CASE_Y);
				case1.setView(p);
				case1.setX(i);
				case1.setY(j);
				System.out.println( case1.getOrientation());
				Label labelOr = new Label(String.valueOf( case1.getOrientation()));
				labelOr.setFont(new Font(9));
				labelOr.setBackground(new Background(new BackgroundFill(Color.WHITE,  new CornerRadii(10),new Insets(0))));
				labelOr.setLayoutX(i * SIZE_CASE_X);
				labelOr.setLayoutY(j * SIZE_CASE_Y);
//				labelOr.setDis
				// p.setFill(c);
				background.getChildren().add(p);
				labels.getChildren().add(labelOr);
//				root.getChildren().addAll(labelOr);

				// Case c = new Case();
			}
		}
		scene.setOnMouseDragged((s) -> drag(s));
		scene.setOnMouseDragExited((s) -> dragExit(s));

		scene.setOnKeyPressed((k) -> keyPressed(k));
		scene.setOnKeyReleased((k) -> keyReleased(k));
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		root.getChildren().add(label);
		root.setTranslateX(-10*60);
		root.setTranslateY(-10*60);
		primaryStage.show();
//		timeline.play();

	}


	
	private Image getRandomImage(Deep deep){
		
		return images[deep.ordinal()];
	
		
		
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

	
}
