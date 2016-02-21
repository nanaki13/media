package org.nanaki.db;

import java.util.HashMap;
import java.util.function.Supplier;

public class SmartMap<T1, T2> extends HashMap<T1, T2>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Supplier<? extends T2> supplier ;
	public T2 getOrCreate(T1 t){
		T2 t2 = get(t);
		if(t2 == null){
			t2 = supplier.get();
			put(t,t2);
		}
		return t2;
	}

}
