package org.jbonnet;

public class Pecheur {
	private int speed;
	private int strenght;
	private int endurance;
	private int luck;
	
	
	/**
	 * @param speed
	 * @param strenght
	 * @param endurance
	 * @param luck
	 */
	public Pecheur(int speed, int strenght, int endurance, int luck) {
		super();
		this.speed = speed;
		this.strenght = strenght;
		this.endurance = endurance;
		this.luck = luck;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * @return the strenght
	 */
	public int getStrenght() {
		return strenght;
	}
	/**
	 * @param strenght the strenght to set
	 */
	public void setStrenght(int strenght) {
		this.strenght = strenght;
	}
	/**
	 * @return the endurance
	 */
	public int getEndurance() {
		return endurance;
	}
	/**
	 * @param endurance the endurance to set
	 */
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}
	/**
	 * @return the luck
	 */
	public int getLuck() {
		return luck;
	}
	/**
	 * @param luck the luck to set
	 */
	public void setLuck(int luck) {
		this.luck = luck;
	}
	
	
}
