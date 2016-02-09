package org.nanaki;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ClassAccess {
	private static final String SET = "set";
	private static final String GET = "get";
	private static final String IS = "is";
	private Class<?> clazz;
	private Map<String, FieldGetterAndSetter> props = new HashMap<>();

	public ClassAccess(Class<?> clazz) {
		super();
		this.clazz = clazz;
		Field[] declaredFields = clazz.getDeclaredFields();
		List<Method> methods = new LinkedList<>(Arrays.asList(clazz.getMethods()));
		filter(methods);
		for (Field f : declaredFields) {
			registerGetterAndSetter(f, methods);
		}
	}

	private void filter(List<Method> methods) {
		Iterator<Method> iterator = methods.iterator();
		while (iterator.hasNext()) {
			Method next = iterator.next();
			int parameterCount = next.getParameterCount();
			String name = next.getName();
			if (parameterCount > 1 || parameterCount == 1 && !name.startsWith(SET) ||parameterCount == 0 && !name.startsWith(GET)
					&& !name.startsWith(IS)) {
				iterator.remove();
			}
		}
	}

	private void registerGetterAndSetter(Field f, List<Method> methods) {
		String fieldName = f.getName();
		String ucFirst = ucFirst(f.getName());
		Iterator<Method> iterator = methods.iterator();
		String getterName = GET+ucFirst;
		String getterNameBoolean = null;
		if(f.getType() == Boolean.class || f.getType() == boolean.class ){
			getterNameBoolean = IS+ucFirst;
		}
		String setterName = SET+ucFirst;
		while (iterator.hasNext()) {
			Method method = iterator.next();
			String name = method.getName();
			if(name.equals(getterName) || name.equals(getterNameBoolean)){
				addGetter(f,fieldName , method);
			}else if(name.equals(setterName)){
				addSetter(f,fieldName , method);
			}
		}

	}

	private void addSetter(Field f, String name, Method method) {
		FieldGetterAndSetter getterAndSetter = init(f,name);
		getterAndSetter.setSetter(method);
		
	}
	private void addGetter(Field f, String name, Method method) {
		FieldGetterAndSetter getterAndSetter = init(f,name);
		getterAndSetter.setGetter(method);
		
	}

	private FieldGetterAndSetter init(Field field,String name) {
		FieldGetterAndSetter fieldGetterAndSetter = props.get(name);
		if(fieldGetterAndSetter == null){
			fieldGetterAndSetter = new FieldGetterAndSetter(field);
			props.put(name, fieldGetterAndSetter);
		}
		return fieldGetterAndSetter;
	}


	private static String ucFirst(String name) {
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
	
	public Object get(String fieldName, Object o ) throws ClassAccessException{
		FieldGetterAndSetter fieldGetterAndSetter = props.get(fieldName);
		if(fieldGetterAndSetter == null){
			throw new ClassAccessException("no field named"+fieldName);
		}else{
			Method getter = fieldGetterAndSetter.getGetter();
			if(getter == null){
				throw new ClassAccessException("no getter for field"+fieldName);
			}else{
				try {
					return getter.invoke(o);
				} catch (ReflectiveOperationException | IllegalArgumentException e) {
					throw new ClassAccessException("invoke exception for field name : "+fieldName);
				}
			}
		}
	}
	/**
	 * Set the value by setter method to the field named fieldName to the object o.
	 * @param fieldName
	 * @param o
	 * @param value
	 * @throws ClassAccessException
	 */
	public void set(String fieldName, Object o ,Object value) throws ClassAccessException{
		FieldGetterAndSetter fieldGetterAndSetter = props.get(fieldName);
		if(fieldGetterAndSetter == null){
			throw new ClassAccessException("no field named"+fieldName);
		}else{
			Method setter = fieldGetterAndSetter.getSetter();
			if(setter == null){
				throw new ClassAccessException("no setter for field"+fieldName);
			}else{
				try {
					setter.invoke(o, value);
				} catch (ReflectiveOperationException | IllegalArgumentException e) {
					throw new ClassAccessException("invoke exception for field name : "+fieldName);
				}
			}
		}
		
	}
	
	public static String buildString(Object o ) throws ClassAccessException{
		ClassAccess ca = new ClassAccess(o.getClass());
		Map<String, FieldGetterAndSetter> props2 = ca.props;
		Set<Entry<String, FieldGetterAndSetter>> entrySet = props2.entrySet();
		StringBuilder b = new StringBuilder();
		Iterator<Entry<String, FieldGetterAndSetter>> iterator = entrySet.iterator();
		while( iterator.hasNext()){
			Entry<String, FieldGetterAndSetter> e= iterator.next();
			b.append( e.getKey()+"="+ca.get(e.getKey(),o));
			if(iterator.hasNext()){
				b.append(", ");
			}
		}
		return b.toString();
		
	}

}
