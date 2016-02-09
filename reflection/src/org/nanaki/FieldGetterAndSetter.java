package org.nanaki;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * This class warp a field with getter and setter methods. 
 * @author jonathan
 *
 */
public class FieldGetterAndSetter {
	private Field field;
	private Method getter;
	private Method setter;
	
	/**
	 * 
	 * @param field
	 */
	public FieldGetterAndSetter(Field field) {
		super();
		this.field = field;
	}

	public Method getGetter() {
		return getter;
	}

	public void setGetter(Method getter) {
		this.getter = getter;
	}

	public Method getSetter() {
		return setter;
	}

	public void setSetter(Method setter) {
		this.setter = setter;
	}
	
	
	
	
}
