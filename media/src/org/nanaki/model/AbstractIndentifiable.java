package org.nanaki.model;

public abstract class AbstractIndentifiable<T> implements Identifiable<T> {
	protected T id;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
	
	
}
