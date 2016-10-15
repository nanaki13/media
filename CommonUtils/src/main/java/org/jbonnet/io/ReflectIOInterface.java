package org.jbonnet.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReflectIOInterface<T> implements ObjectIOInterface<T> {

	private Class<? extends T> clazz;
	private Map<String, Method> setters;
	private Map<String, Method> getters;
	private static String SET = "set";
	private static String GET = "get";
	private static String IS = "is";
	private static Map<Class<?>, ReflectIOInterface<?>> instances = Collections.synchronizedMap(new HashMap<>());

	public static <U> ReflectIOInterface<U> getInstance(Class<U> c) {
		ReflectIOInterface<?> reflectIOInterface = instances.get(c);
		if (reflectIOInterface == null) {
			reflectIOInterface = new ReflectIOInterface<>(c);
			instances.put(c, reflectIOInterface);
		}
		return (ReflectIOInterface<U>) reflectIOInterface;
	}

	private ReflectIOInterface(Class<? extends T> clazz) {
		this.clazz = clazz;
		Method[] methods = clazz.getMethods();
		List<Method> l = new ArrayList<>(Arrays.asList(methods));
		Function<Method, String> keyMapperSetter = (m) -> lcFirst(m.getName().substring(3));
		Function<Method, String> keyMapperGetter = (m) -> lcFirst(
				m.getName().startsWith(GET) ? m.getName().substring(3) : m.getName().substring(2));
		Function<Method, Method> valueMapper = (m) -> m;
		setters = l.stream().filter((m) -> !Modifier.isStatic(m.getModifiers()) && m.getParameterTypes().length == 1
				&& m.getName().startsWith(SET)).collect(Collectors.toMap(keyMapperSetter, valueMapper));
		getters = l.stream()
				.filter((m) -> !Modifier.isStatic(m.getModifiers()) && m.getParameterTypes().length == 0
						&& (m.getName().startsWith(GET)) || m.getName().startsWith(IS))
				.collect(Collectors.toMap(keyMapperGetter, valueMapper));

	}

	private String lcFirst(String object) {
		return Character.toLowerCase(object.charAt(0)) + object.substring(1);
	}

	@Override
	public Object getFrom(String field, T read) throws ReflectiveOperationException {
		if (!getters.containsKey(field)) {
			throw new IllegalArgumentException("field : no getter field");
		}
		return getters.get(field).invoke(read);

	}

	@Override
	public void setTo(String field, T write, Object value) throws ReflectiveOperationException {
		if (!setters.containsKey(field)) {
			throw new IllegalArgumentException("no getter field");
		}
		setters.get(field).invoke(write, value);

	}

	@Override
	public Collection<String> getFields() {
		return new HashSet<>(getters.keySet());
	}
	
	public T readProperties(String p) throws IOException{
		
		try(InputStream i = new FileInputStream(p)){
			T newInstance = clazz.newInstance();
			Properties properties = new Properties();
			properties.load(i);
			Set<Entry<Object,Object>> entrySet = properties.entrySet();
			for(Entry<Object,Object> o : entrySet){
				setTo(o.getKey().toString(),newInstance , o.getValue());
			}
			return newInstance;
		} catch (ReflectiveOperationException e) {
			throw new IOException(e);
		} 
	}

}
