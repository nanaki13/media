package application;

import java.util.Iterator;
import java.util.List;

public class Plateau {
	private ObjectPlateauInterface[][] objectPlateaus;
	private SnakeHead snakeHead;
	private List<Ring> body;
	private List<Fruit> fruits;

	public void nextState(Direction d) {
		int dx= 0;
		int dy = 0;
		switch (d) {
		case UP:
			dx = 0;
			dy = -1;
			break;
		case DOWN:
			dx = 0;
			dy = 1;
			break;
		case LEFT:
			dx = -1;
			dy = 0;
			break;
		case RIGHT:
			dx = 1;
			dy = 0;
			break;

		default:
			break;
			
		}
		int[] prev = snakeHead.addToPos(dx, dy);
		propage(prev);
		
	}

	private void propage(int[] prev) {
		Iterator<Ring> iterator = body.iterator();
		while (iterator.hasNext()) {
			Ring ring =  iterator.next();
			prev = ring.setPos(prev);
			
		}
		
		
	}
}
