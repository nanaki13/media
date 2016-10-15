package application;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class Snake extends Group {

	private static final short RIGHT = 0;
	private static final short LEFT = 1;
	private static final short UP = 2;
	private static final short DOWN = 3;
	private short side = RIGHT;
	private Group eyes;
	private Group head;
	private List<Point2D> histo = new ArrayList<>();
	// private Circle circle;
	private double ringSize = 15;
	private boolean newRing;
	private int countFromNew;
	private int moveCount = 0;
	private double headSize = 15;
	private int newRingMoveCount;
	private LinkedList<Ring> circles = new LinkedList<>();
	private List<Integer> countFromNews = new ArrayList<>();
	private Ring circle;
	private boolean addingNewRing;
	private int ecart;
	private List<Mvt> mvts = new ArrayList<>();
	private Mvt currentMvt;

	private static class Ring extends Circle {
		public Ring(double i, double j, double ringSize) {
			super(i, j, ringSize);
		}

		public int delta;
		public int indexMvt;
	};

	public Snake() {

		head = new Group();
		Circle c = new Circle();
		c.setCenterX(0);
		c.setCenterY(0);
		c.setRadius(1);
		c.setFill(Color.BLACK);
		Circle tour = new Circle();
		tour.setCenterX(0);
		tour.setCenterY(0);
		tour.setRadius(headSize);
		tour.setFill(Color.DARKGREEN);
		eyes = new Group();
		Ellipse eye1 = new Ellipse();
		eye1.setFill(Color.YELLOW);
		eye1.setCenterX(4);
		eye1.setCenterY(-5);
		eye1.setRadiusX(2);
		eye1.setRadiusY(4);

		Ellipse eye2 = new Ellipse();
		eye2.setFill(Color.YELLOW);
		eye2.setCenterX(4);
		eye2.setCenterY(5);
		eye2.setRadiusX(2);
		eye2.setRadiusY(4);
		Ellipse pup1 = new Ellipse();
		pup1.setFill(Color.GREEN);
		pup1.setCenterX(4);
		pup1.setCenterY(-5);
		pup1.setRadiusX(2);
		pup1.setRadiusY(2);

		Ellipse pup2 = new Ellipse();
		pup2.setFill(Color.GREEN);
		pup2.setCenterX(4);
		pup2.setCenterY(5);
		pup2.setRadiusX(2);
		pup2.setRadiusY(2);
		head.getChildren().addAll(tour, eye1, eye2, pup1, pup2, c);
		head.rotateProperty().set(0);
		getChildren().addAll(head);
	}

	public void right() {
		head.rotateProperty().set(0);
	}

	public void left() {
		head.rotateProperty().set(180);
	}

	public void up() {
		head.rotateProperty().set(-90);
	}

	public void down() {
		head.rotateProperty().set(90);

	}

	public void addRing() {
		if(!addingNewRing){
			newRing = true;
			countFromNew = 0;
			newRingMoveCount = moveCount;
			circle = new Ring(0, 0, ringSize);
			if (!circles.isEmpty()) {
				circle.setTranslateX(circles.getLast().getTranslateX());
				circle.setTranslateY(circles.getLast().getTranslateY());
			} else {
				circle.setTranslateX(head.getTranslateX());
				circle.setTranslateY(head.getTranslateY());
			}

			getChildren().add(circle);
		}
		

	}

	public void move(double dx, double dy, int nbFrame) {

		if (currentMvt == null) {
			
			currentMvt = new Mvt();
			currentMvt.setDx(dx);
			currentMvt.setDy(dy);
			currentMvt.setNbFrameStart(nbFrame);
			mvts.add(currentMvt);
			System.out.println(mvts);
		} else if(currentMvt.getDx() != dx || currentMvt.getDy() != dy)  {
			
			currentMvt.setNbFrameStop(nbFrame - 1);
			currentMvt = new Mvt();
			currentMvt.setDx(dx);
			currentMvt.setDy(dy);
			currentMvt.setNbFrameStart(nbFrame);
			mvts.add(currentMvt);
			System.out.println(mvts);
		}
		if (newRing) {
			addingNewRing = true;
			if (countFromNew>10) {
				newRing = false;
				addingNewRing = false;
				
				if(circles.isEmpty()){
					circle.delta =  countFromNew;
					
				}else{
					circle.delta = countFromNew + circles.getLast().delta;
				}
				circles.add(circle);
				int ind = 0;
				for(Mvt mvt :mvts){
					if(mvt.getNbFrameStart() <=  nbFrame - circle.delta && mvt.getNbFrameStop() >= nbFrame - circle.delta){
						circle.indexMvt = ind;
					}
					ind++;
				}
			} else {
				countFromNew++;
			}
		}
		if (!circles.isEmpty()) {

			
			for (Ring c : circles) {

				if (c.delta  != 0) {
					Mvt mvt = mvts.get(c.indexMvt);
					if(nbFrame - c.delta < mvt.getNbFrameStop() || mvt.getNbFrameStop() == 0){
						
					}else{
						c.indexMvt++;
						mvt = mvts.get(c.indexMvt);
					}
					c.setTranslateX(c.getTranslateX() + mvt.getDx());
					c.setTranslateY(c.getTranslateY() + mvt.getDy());
					

				}
			
				

			}
			
			

		}
		moveCount++;

		
		
		head.setTranslateX(head.getTranslateX() + dx);
		head.setTranslateY(head.getTranslateY() + dy);
		if (dx > 0) {
			right();
		} else if (dx < 0) {
			left();
		}
		if (dy > 0) {
			down();
		} else if (dy < 0) {
			up();
		}
		
	}

	public Node getHead() {
		// TODO Auto-generated method stub
		return head;
	}

}
