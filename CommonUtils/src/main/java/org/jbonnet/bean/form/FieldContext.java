package org.jbonnet.bean.form;

public class FieldContext {
	private String name;
	private String label;
	private boolean hide;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the hide
	 */
	public boolean isHide() {
		return hide;
	}
	/**
	 * @param hide the hide to set
	 */
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	/**
	 * @param name
	 */
	public FieldContext(String name) {
		super();
		this.name = name;
	}
	public FieldContext(String name, String label, boolean hide) {
		this.name=name;
		this.label=label;
		this.hide=hide;
	}
	public boolean isSimple() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
