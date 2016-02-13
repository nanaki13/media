package org.nanaki.model;

public abstract class AbstractNomableAndIdentifiable<T> extends AbstractIndentifiable<T> implements Nomable, Identifiable<T> {
	protected String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
