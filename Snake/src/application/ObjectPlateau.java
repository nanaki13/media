package application;

public class ObjectPlateau {
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int[] addToPos(int dx, int dy){
		int[] prev = new int[2];
		prev[0] = x;
		prev[1] = y;
		x+=dx;
		y+=dy;
		return prev;
	}
}
