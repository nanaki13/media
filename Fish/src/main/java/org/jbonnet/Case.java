package org.jbonnet;

import javafx.scene.paint.Paint;

public class Case<T> {
	
	private int x;
	private int y;
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	private T object;
	/**
	 * @param color
	 * @param pane
	 */
	public Case() {

	}
	/**
	 * @return the object
	 */
	public T getObject() {
		return object;
	}
	/**
	 * @param object the object to set
	 */
	public void setObject(T object) {
		this.object = object;
	}
	
	
}
