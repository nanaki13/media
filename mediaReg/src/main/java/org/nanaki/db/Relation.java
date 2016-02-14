package org.nanaki.db;

public class Relation<T,U> {
	private SQLManager<T> manager1;
	private SQLManager<U> manager2;
	
	public Relation() {
		
	}

	public Relation(SQLManager<T> manager1,
			SQLManager<U> manager2) {
		super();
		this.manager1 = manager1;
		this.manager2 = manager2;
	}
	
	
}
