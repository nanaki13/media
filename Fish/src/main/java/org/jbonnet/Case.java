package org.jbonnet;

public class Case<Model,View> {
	
	private int x;
	private int y;
	private View view;
	
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
	private Model model;
	/**
	 * @param color
	 * @param pane
	 */
	public Case() {

	}
	/**
	 * @return the object
	 */
	public Model getModel() {
		return model;
	}
	/**
	 * @param object the object to set
	 */
	public void setModel(Model object) {
		this.model = object;
	}
	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}
	/**
	 * @param view the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}
	
	
	
}
