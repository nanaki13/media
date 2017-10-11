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


	
	private static final Background LABEL_BACKGROUND = new Background(new BackgroundFill(Color.WHITE,  new CornerRadii(10),new Insets(0)));
	public static final int SIZE_CASE_X = 60;
	public static final int SIZE_CASE_Y = 60;
	public static final int MARGE = 0;
	
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
		label.setBackground(LABEL_BACKGROUND);
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


				
				Plateau<Case<CaseContent, Group>> pl = fishEngine.getPlateau();
				Case<CaseContent,Group> case1 =  pl.getCase(i, j);
				
				Image c = Images.getBackgrounImage(case1.getModel().getDeep(), i, j, fishEngine.getSizeX()-1, fishEngine.getSizeY()-1);
				ImageView p = new ImageView(c);
				Group g = new Group();
				g.setLayoutX(i * SIZE_CASE_X);
				g.setLayoutY(j * SIZE_CASE_Y);
				
				g.getChildren().add(p);
				case1.setView(g);
				case1.setX(i);
				case1.setY(j);
				Fish f = case1.getModel().getFish();
				if(f != null){
					System.out.println(case1);
					Label labelF = new Label("POISSON");
					labelF.setFont(new Font(9));
					labelF.setBackground(LABEL_BACKGROUND);
					labelF.setLayoutX(SIZE_CASE_X/2);
					labelF.setLayoutY(SIZE_CASE_Y/2);
					g.getChildren().add(labelF);
				}
				Label labelOr = new Label(String.valueOf( case1.getOrientation()));
				labelOr.setFont(new Font(9));
				labelOr.setBackground(LABEL_BACKGROUND);
				labelOr.setLayoutX(i * SIZE_CASE_X);
				labelOr.setLayoutY(j * SIZE_CASE_Y);
//				labelOr.setDis
				// p.setFill(c);
				background.getChildren().add(g);
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

				root.setTranslateY(root.getTranslateY() + dy*2);
			}

			//
			double dx = s.getScreenX() - prviousX;
			prviousX = s.getScreenX();
			if (Math.abs(dx) < 30) {

				root.setTranslateX(root.getTranslateX() + dx*2);
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
