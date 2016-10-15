package application;

public class Mvt {
	private double dx;
	private double dy;
	private int nbFrameStart;
	private int nbFrameStop;
	public double getDx() {
		return dx;
	}
	public void setDx(double dx) {
		this.dx = dx;
	}
	public double getDy() {
		return dy;
	}
	public void setDy(double dy) {
		this.dy = dy;
	}
	public int getNbFrameStart() {
		return nbFrameStart;
	}
	public void setNbFrameStart(int nbFrameStart) {
		this.nbFrameStart = nbFrameStart;
	}
	public int getNbFrameStop() {
		return nbFrameStop;
	}
	public void setNbFrameStop(int nbFrameStop) {
		this.nbFrameStop = nbFrameStop;
	}
	@Override
	public String toString() {
		return "Mvt [dx=" + dx + ", dy=" + dy + ", nbFrameStart=" + nbFrameStart + ", nbFrameStop=" + nbFrameStop + "]";
	}
	
	
}
